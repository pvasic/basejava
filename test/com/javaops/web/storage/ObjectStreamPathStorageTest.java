package com.javaops.web.storage;

/**
 * @author Vasichkin Pavel
 */
public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(DIR_NAME));
    }
}