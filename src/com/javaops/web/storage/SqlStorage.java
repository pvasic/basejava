package com.javaops.web.storage;

import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.exception.StorageException;
import com.javaops.web.model.ContactType;
import com.javaops.web.model.Resume;
import com.javaops.web.sql.SqlHelper;

import java.sql.*;
import java.util.*;

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
        return sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        "      LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "      WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    if (rs.getString("type") != null) {
                        do {
                            r.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
                        } while (rs.next());
                    }
                    return r;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                        writeUuidFullname(ps, resume);
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(resume.getUuid());
                        }
                    }
                    writeContacts(conn, resume, "UPDATE contact SET value = ? WHERE resume_uuid = ? AND type = ?");
                    return null;
                }
        );
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (full_name, uuid) VALUES (?,?)")) {
                        writeUuidFullname(ps, resume);
                        ps.execute();
                    }
                    writeContacts(conn, resume, "INSERT INTO contact (value, resume_uuid, type) VALUES (?,?,?)");
                    return null;
                }
        );
    }

    private void writeUuidFullname(PreparedStatement ps, Resume resume) throws SQLException {
        ps.setString(1, resume.getFullName());
        ps.setString(2, resume.getUuid());
    }

    private void writeContacts(Connection conn, Resume resume, String s) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(s)) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, e.getValue());
                ps.setString(2, resume.getUuid());
                ps.setString(3, e.getKey().name());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute("DELETE FROM resume WHERE uuid=?", ps -> {
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
                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY uuid")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String uuid = rs.getString("uuid");
                            resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                        }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact c ORDER BY resume_uuid")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            resumes.computeIfPresent(rs.getString("resume_uuid"), (s, r) -> {
                                try {
                                    r.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
                                } catch (SQLException e) {
                                    throw new StorageException(e);
                                }
                                return r;
                            });
                        }
                    }
                    return null;
                }
        );
        List<Resume> listResume = new ArrayList<>(resumes.values());
        listResume.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return listResume;
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
