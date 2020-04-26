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
             PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM resume")) {
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            throw new ExistStorageException("uuid2" + e);
        }



    }
}
