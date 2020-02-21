package com.javaops.web.storage;

/**
 * @author Vasichkin Pavel
 */
public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(DIR_NAME, new ObjectStreamSerializer()));
    }
}