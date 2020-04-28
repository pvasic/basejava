package com.javaops.web.storage;

import com.javaops.web.config.Config;

/**
 * @author Vasichkin Pavel
 */
public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}