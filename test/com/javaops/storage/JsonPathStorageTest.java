package com.javaops.storage;

import com.javaops.storage.serializer.JsonStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}