package com.javaops.web.run;

import com.javaops.web.config.Config;
import com.javaops.web.sql.ConnectionFactory;
import com.javaops.web.sql.SqlHelper;

import java.sql.*;
import java.util.Properties;

public class MainPreparedStatement {
    private static final Properties PROPS = Config.get().getProps();
    private static final ConnectionFactory connectonFactory;

    static {
        connectonFactory = () -> DriverManager.getConnection(PROPS.getProperty("db.url"), PROPS.getProperty("db.user"), PROPS.getProperty("db.password"));
    }

    public static void main(String[] args) {
        SqlHelper sqlHelper = SqlHelper.getSqlHelper(connectonFactory);
        sqlHelper.execute(
                "DELETE FROM resume", (ps) -> {
                    ps.execute();
                    return null;
                }
        );



    }
}
