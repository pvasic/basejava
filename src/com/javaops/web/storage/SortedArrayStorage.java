package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    public SortedArrayStorage() {
        storage = new Resume[STORAGE_LIMIT];
    }

    @Override
    protected void saveAndMove(Resume resume, int index) {
        int i = size;
        for (; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = resume;

    }

    @Override
    protected void deleteAndMove(int index) {
        for (; index < size; index++) {
            storage[index] = storage[index + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
