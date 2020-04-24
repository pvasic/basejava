package com.javaops.web.storage;

import com.javaops.web.storage.serializer.DataStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(new SqlStorage(PROPS.getProperty("db.url"), PROPS.getProperty("db.user"), PROPS.getProperty("db.password")));
    }
}