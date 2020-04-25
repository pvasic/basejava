package com.javaops.web.run;

import com.javaops.web.config.Config;
import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.sql.ConnectionFactory;

import java.sql.*;
import java.util.Properties;

public class MainPreparedStatement {
    private static final Properties PROPS = Config.get().getProps();
    private static final ConnectionFactory connectonFactory;

    static {
        connectonFactory = () -> DriverManager.getConnection(PROPS.getProperty("db.url"), PROPS.getProperty("db.user"), PROPS.getProperty("db.password"));
    }

    public static void main(String[] args) {
        try (Connection conn = connectonFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES  (?,?)")) {
            ps.setString(1, "uuid7");
            ps.setString(2, "tttttttttt");
            ps.execute();
        } catch (SQLException e) {
            throw new ExistStorageException("uuid2" + e);
        }

    }
}
