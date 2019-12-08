package com.javaops.web.storage;

import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.exception.OverflowStorageException;
import com.javaops.web.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private final static String uuid1 = "uuid1";
    private final static Resume r1 = new Resume(uuid1);
    private final static String uuid2 = "uuid2";
    private final static Resume r2 = new Resume(uuid2);
    private final static String uuid3 = "uuid3";
    private final static Resume r3 = new Resume(uuid3);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUP() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] array = {r1, r2, r3};
        assertArrayEquals(array, storage.getAll());
        assertEquals(array.length, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        assertEquals(r2, storage.get(uuid2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        assertEquals(r4, storage.get("uuid4"));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist(){
        Resume resume = new Resume(uuid2);
        storage.save(resume);
    }

    @Test(expected = OverflowStorageException.class)
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
    }

    @Test
    public void update() {
        Resume actual = new Resume(uuid2);
        Resume unexpected = storage.get(uuid2);
        storage.update(actual);
        assertNotSame(unexpected, storage.get(uuid2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist(){
        Resume dummy = new Resume("dummy");
        storage.update(dummy);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(uuid2);
        storage.get(uuid2);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }
}