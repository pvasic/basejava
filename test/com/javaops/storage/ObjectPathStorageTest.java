package com.javaops.storage;

import com.javaops.storage.serializer.ObjectStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}