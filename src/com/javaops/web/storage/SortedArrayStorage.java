package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void saveAndMove(Resume resume) {

    }

    @Override
    protected void deleteAndMove(int index) {
        for (;index < size;index++) {
            storage[index] = storage[index+1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
