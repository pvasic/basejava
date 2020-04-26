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

    private static SqlHelper SQL_HELPER_INSTANCE;

    private final ConnectionFactory connectionFactory;

    private SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static SqlHelper getSqlHelper(ConnectionFactory connectionFactory) {
        if (SQL_HELPER_INSTANCE == null) {
            return new SqlHelper(connectionFactory);
        }
        return SQL_HELPER_INSTANCE;
    }

    public <T> Object execute(String sqlString, SqlStorageBlockOfCode<T> blockOfCode) {
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
