package com.javaops.storage;

import com.javaops.exception.NotExistStorageException;
import com.javaops.model.*;
import com.javaops.sql.SqlHelper;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "    SELECT * FROM resume r " +
                    "      LEFT JOIN contact c " +
                    "        ON r.uuid = c.resume_uuid " +
                    "      WHERE r.uuid =? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
                do {
                    addContact(rs, resume);
                } while (rs.next());
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                        insertUuidFullname(ps, resume);
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(resume.getUuid());
                        }
                    }
                    deleteFromTable(conn, resume);
                    insertContacts(conn, resume);
                    insertSection(conn, resume);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (full_name, uuid) VALUES (?,?)")) {
                        insertUuidFullname(ps, resume);
                        ps.execute();
                    }
                    insertContacts(conn, resume);
                    insertSection(conn, resume);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute("DELETE FROM resume r WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> resumes = new LinkedHashMap<>();
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY r.full_name, uuid")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String uuid = rs.getString("uuid");
                            resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                        }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            Resume r = resumes.get(rs.getString("resume_uuid"));
                            addContact(rs, r);
                        }
                    }

                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            Resume r = resumes.get(rs.getString("resume_uuid"));
                            addSection(rs, r);
                        }
                    }
                    return null;
                }
        );
        return new ArrayList<>(resumes.values());
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertUuidFullname(PreparedStatement ps, Resume resume) throws SQLException {
        ps.setString(1, resume.getFullName());
        ps.setString(2, resume.getUuid());
    }

    private void deleteFromTable(Connection conn, Resume r) {
        String uuid = r.getUuid();
        sqlHelper.execute("" +
                        "DELETE  FROM contact WHERE resume_uuid=?;" +
                        "DELETE  FROM section WHERE resume_uuid=?"
                , ps -> {
                    ps.setString(1, uuid);
                    ps.setString(2, uuid);
                    ps.execute();
                    return null;
                });
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            r.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void insertContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (value, resume_uuid, type) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, e.getValue());
                ps.setString(2, resume.getUuid());
                ps.setString(3, e.getKey().name());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType sectionType = SectionType.valueOf(rs.getString("type"));
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    r.addSection(SectionType.valueOf(rs.getString("type")), new TextSection(content));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    r.addSection(SectionType.valueOf(rs.getString("type")), new ListSection(Stream.of(content.split("\n")).collect(Collectors.toList())));
                    break;
            }
        }
    }

    private void insertSection(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
                SectionType sectionType = e.getKey();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        setUuidAndType(ps, resume, sectionType);
                        ps.setString(3, ((TextSection) e.getValue()).getContent());
                        ps.addBatch();
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        setUuidAndType(ps, resume, sectionType);
                        ps.setString(3, (String.join("\n", ((ListSection) e.getValue()).getItems())));
                        ps.addBatch();
                        break;
                }
            }
            ps.executeBatch();
        }
    }

    private void setUuidAndType(PreparedStatement ps, Resume resume, SectionType sectionType) throws SQLException {
        ps.setString(1, resume.getUuid());
        ps.setString(2, sectionType.name());
    }
}
