package com.javaops.web.sql;

import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;
    private final String sqlString;

    public SqlHelper(ConnectionFactory connectionFactory, String sqlString) {
        this.sqlString = sqlString;
        this.connectionFactory = connectionFactory;
    }

    public <T> Object execute(SqlStorageBlockOfCode<T> blockOfCode) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            return blockOfCode.execute(ps);
        } catch (NotExistStorageException e) {
            throw new NotExistStorageException(e.toString());
        } catch (PSQLException e) {
            throw new ExistStorageException(e.toString());
        } catch (StorageException | SQLException e) {
            throw new StorageException(e);
        }

    }
}
