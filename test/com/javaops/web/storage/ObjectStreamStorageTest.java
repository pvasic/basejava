package com.javaops.web.storage;

/**
 * @author Vasichkin Pavel
 */
public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}