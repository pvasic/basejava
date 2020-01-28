package com.javaops.web.storage;

import com.javaops.web.exception.OverflowStorageException;
import com.javaops.web.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vasichkin Pavel
 * Abstract storage based on arrays for Resume
 */

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    protected List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[(index)];
    }

    @Override
    protected void doSave(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new OverflowStorageException(resume.getUuid());
        }
        insertElement(resume, -(index) - 1);
        size++;
    }

    @Override
    protected void doUpdate(Integer index, Resume resume) {
        storage[(index)] = resume;
    }

    @Override
    protected void doDelete(Integer index) {
        removeElement((index));
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return (searchKey) > -1;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void removeElement(int index);
}
