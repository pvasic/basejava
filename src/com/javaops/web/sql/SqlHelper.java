package com.javaops.web.sql;

import com.javaops.web.exception.StorageException;
import com.javaops.web.model.Resume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private ConnectionFactory connectionFactory;
    private String sqlString;

    public SqlHelper(ConnectionFactory connectionFactory, String sqlString) {
        this.sqlString = sqlString;
        this.connectionFactory = connectionFactory;
    }

    public Resume execute(SqlStorageBlockOfCode blockOfCode) {
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlString)) {
            return blockOfCode.execute(ps);
        } catch (StorageException | SQLException e) {
            throw new StorageException(e);
        }

    }
}
