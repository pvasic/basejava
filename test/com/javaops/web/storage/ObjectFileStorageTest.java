package com.javaops.web.storage;

import com.javaops.web.storage.serializer.ObjectStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}