package com.javaops.web.storage;

import com.javaops.web.exception.OverflowStorageException;
import com.javaops.web.model.Resume;

import java.util.Arrays;

/**
 * @author Vasichkin Pavel
 * Abstract storage based on arrays for Resume
 */

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected static final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected static int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    @Override
    public void saveResume(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new OverflowStorageException(resume.getUuid());
        }
        insertElement(resume, - ((Integer) index) -1);
        size++;
    }

    @Override
    public void updateResume(Object index, Resume resume) {
        storage[(Integer) index] = resume;
    }

    @Override
    public void deleteResume(Object index) {
        removeElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isKey(Object key){
        return ((Integer) key) > -1;
    };

    @Override
    protected abstract Object searchKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void removeElement(int index);
}
