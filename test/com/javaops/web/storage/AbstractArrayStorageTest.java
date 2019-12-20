package com.javaops.web.storage;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    /*@Test(expected = OverflowStorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (OverflowStorageException e) {
            fail("There should be no overflow" + e);
        }
        storage.save(new Resume());
    }*/
}