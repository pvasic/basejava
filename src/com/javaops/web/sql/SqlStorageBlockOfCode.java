package com.javaops.web.sql;

import com.javaops.web.model.Resume;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlStorageBlockOfCode {
    Resume execute(PreparedStatement ps) throws SQLException;
}
