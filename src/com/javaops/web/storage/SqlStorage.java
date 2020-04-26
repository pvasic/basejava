package com.javaops.web.storage;

import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.model.Resume;
import com.javaops.web.sql.ConnectionFactory;
import com.javaops.web.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        SqlHelper sqlHelper = new SqlHelper(connectionFactory, "DELETE FROM resume");
        sqlHelper.execute(
                (ps) -> {
                    ps.execute();
                    return null;
                });
    }

    @Override
    public Resume get(String uuid) {
        SqlHelper sqlHelper = new SqlHelper(connectionFactory, "SELECT * FROM resume r WHERE uuid = ?");
        return (Resume) sqlHelper.execute(
                (ps) -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    return new Resume(uuid, rs.getString("full_name"));
                });
    }

    @Override
    public void update(Resume resume) {
        SqlHelper sqlHelper = new SqlHelper(connectionFactory, "UPDATE resume SET full_name = ? WHERE uuid = ?");
        sqlHelper.execute(
                (ps) -> {
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getUuid());
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(resume.getUuid());
                    }
                    return null;
                });
    }

    @Override
    public void save(Resume resume) {
        SqlHelper sqlHelper = new SqlHelper(connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES  (?,?)");
        sqlHelper.execute(
                (ps) -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                    return null;
                });
    }

    @Override
    public void delete(String uuid) {
        SqlHelper sqlHelper = new SqlHelper(connectionFactory, "DELETE from resume WHERE uuid = ?");
        sqlHelper.execute(
                (ps) -> {
                    ps.setString(1, uuid);
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        SqlHelper sqlHelper = new SqlHelper(connectionFactory, "SELECT * from resume order by full_name, uuid");
        return (List<Resume>) sqlHelper.execute(
                (ps) -> {
                    ResultSet rs = ps.executeQuery();
                    List<Resume> resumes = new ArrayList<>();
                    while (rs.next()) {
                        resumes.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
                    }
                    return resumes;
                });
    }

    @Override
    public int size() {
        SqlHelper sqlHelper = new SqlHelper(connectionFactory, "SELECT count(*) FROM resume");
        return (int) sqlHelper.execute(
                (ps) -> {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return 0;
                    }
                });
    }
}
