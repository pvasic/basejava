package com.javaops.web.storage;

import com.javaops.web.storage.serializer.ObjectStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}