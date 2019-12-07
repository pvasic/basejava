package com.javaops.web.storage;

import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.exception.StackOverFlowStorageException;
import com.javaops.web.model.Resume;

import java.util.Arrays;

/**
 * @author Vasichkin Pavel
 * Abstract storage for Resume
 */

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected static final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected static int size;

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
            throw new StackOverFlowStorageException(resume.getUuid());
        }
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            throw new ExistStorageException(resume.getUuid());
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
