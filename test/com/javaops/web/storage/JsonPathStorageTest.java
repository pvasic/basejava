package com.javaops.web.storage;

import com.javaops.web.storage.serializer.JsonStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}