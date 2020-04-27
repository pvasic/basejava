package com.javaops.web.sql;

import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String sqlString, SqlStorageBlockOfCode<T> blockOfCode) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            return blockOfCode.execute(ps);
        } catch (PSQLException e) {
            if (e.getServerErrorMessage().getSQLState().equals("23505")) {
                throw new ExistStorageException(e.toString());
            }
            throw new StorageException(e);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
