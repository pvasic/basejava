package com.javaops.web.storage;

/**
 * @author Vasichkin Pavel
 */
public class PathStorageTest extends AbstractStorageTest {
    private final static PathStorage PATH_STORAGE;

    static {
        PATH_STORAGE = new PathStorage(DIR_NAME, new ObjectStreamStorage());
    }

    public PathStorageTest() {
        super(PATH_STORAGE);
    }
}