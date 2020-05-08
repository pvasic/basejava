package com.javaops.storage;

import com.javaops.storage.serializer.ObjectStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}