package com.javaops.web.storage;

/**
 * @author Vasichkin Pavel
 */
public class ObjectStreamFileStorageTest extends AbstractStorageTest{

    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR));
    }
}