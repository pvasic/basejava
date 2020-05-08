package com.javaops.storage;

import com.javaops.config.Config;

/**
 * @author Vasichkin Pavel
 */
public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}