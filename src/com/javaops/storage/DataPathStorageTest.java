package com.javaops.storage;

import com.javaops.storage.serializer.DataStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}