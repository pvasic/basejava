package com.javaops.web.storage;

import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.model.Resume;

import java.util.Arrays;

/**
 * @author Vasichkin Pavel
 */

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("The database is full! Free memory.");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            System.out.println("Resume " + resume.getUuid() + " already exists.");
            return;
        }
        index = -index - 1;
        insertElement(resume, index);
        size++;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            removeElement(index);
            storage[size - 1] = null;
            size--;
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void insertElement(Resume resume, int index);
    protected abstract void removeElement(int index);
    protected abstract int getIndex(String uuid);
}
