package com.javaops.web.storage;

import java.io.File;

/**
 * @author Vasichkin Pavel
 */
public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(new File(DIR_NAME), new ObjectStreamSerializer()));
    }
}