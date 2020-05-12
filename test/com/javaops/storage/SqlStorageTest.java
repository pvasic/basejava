package com.javaops.storage;

import com.javaops.config.Configuration;

/**
 * @author Vasichkin Pavel
 */
public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Configuration.get().getStorage());
    }
}