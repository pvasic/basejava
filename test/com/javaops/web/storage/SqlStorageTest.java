package com.javaops.web.storage;

/**
 * @author Vasichkin Pavel
 */
public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(new SqlStorage(PROPS.getProperty("db.url"), PROPS.getProperty("db.user"), PROPS.getProperty("db.password")));
    }
}