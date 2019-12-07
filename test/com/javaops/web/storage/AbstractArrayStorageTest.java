package com.javaops.web.storage;

import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.exception.StackOverFlowStorageException;
import com.javaops.web.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private final Resume r1 = new Resume("uuid1");
    private final Resume r2 = new Resume("uuid2");
    private final Resume r3 = new Resume("uuid3");

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
    }

    @Test
    public void clear() {
        storage.clear();
        for (Resume resume : storage.getAll()) {
            assertNull(resume);
        }
    }

    @Test
    public void get() {
        assertEquals(r2, storage.get("uuid2"));
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        assertEquals(r4, storage.get("uuid4"));
    }

    @Test
    public void update() {
        Resume r4 = new Resume("uuid2");
        storage.update(r4);
        assertEquals(r4, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete("uuid2");
        storage.get("uuid2");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = StackOverFlowStorageException.class)
    public void stackOverFlow() throws Exception {
        Field field = Class.forName("com.javaops.web.storage.AbstractArrayStorage").getDeclaredField("STORAGE_LIMIT");
        field.setAccessible(true);
        storage.clear();
        try {
            for (int i = 0; i < field.getInt(field); i++) {
                storage.save(new Resume());
            }
        } catch (StackOverFlowStorageException e) {
            fail("There should be no overflow" + e);
        }
        storage.save(new Resume());
    }
}