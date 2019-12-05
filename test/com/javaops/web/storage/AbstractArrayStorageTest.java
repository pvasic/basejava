package com.javaops.web.storage;

import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest() throws IllegalAccessException {
        Class clazz = this.getClass();
        Field field = clazz.getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(this, this);
        field.setAccessible(false);
    }

    @Before
    public void setUP() throws Exception {

        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void get() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}