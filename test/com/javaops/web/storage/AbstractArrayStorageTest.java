package com.javaops.web.storage;

import org.junit.experimental.categories.Category;

@Category(CategoryArray.class)
public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }
}