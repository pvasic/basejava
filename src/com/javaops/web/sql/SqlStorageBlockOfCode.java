package com.javaops.web.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlStorageBlockOfCode<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
