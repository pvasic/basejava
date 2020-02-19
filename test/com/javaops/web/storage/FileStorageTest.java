package com.javaops.web.storage;

import static org.junit.Assert.*;

/**
 * @author Vasichkin Pavel
 */
public class FileStorageTest extends AbstractStorageTest {
    private final static FileStorage FILE_STORAGE;

    static {
        FILE_STORAGE = new FileStorage(STORAGE_DIR, new ObjectStreamStorage());
    }

    public FileStorageTest() {
        super(FILE_STORAGE);
    }
}