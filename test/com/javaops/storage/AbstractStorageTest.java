package com.javaops.storage;

import com.javaops.exception.ExistStorageException;
import com.javaops.exception.NotExistStorageException;
import com.javaops.model.ContactType;
import com.javaops.model.Resume;
import com.javaops.config.Config;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static com.javaops.storage.ResumeTestData.*;

/**
 * @author Vasichkin Pavel
 */
public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = getRESUME1(UUID_1, FULL_NAME_1);
        RESUME_2 = getRESUME2(UUID_2, FULL_NAME_2);
        RESUME_3 = getRESUME3(UUID_3, FULL_NAME_3);
        RESUME_4 = getRESUME4(UUID_4, FULL_NAME_4);
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getAll() {
        List<Resume> array = storage.getAllSorted();
        assertEquals(3, array.size());
        assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3}, array.toArray());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume expected = ResumeTestData.getRESUME4(UUID_2, FULL_NAME_UPDATE);
        expected.getContacts().remove(ContactType.EMAIL);
        expected.getContacts().remove(ContactType.SKYPE);
        expected.setContact(ContactType.LINKEDIN, LINKEDLN_UPDATE);
        storage.update(expected);
        assertEquals(expected, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume dummy = new Resume("dummy", "dummy");
        storage.update(dummy);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}