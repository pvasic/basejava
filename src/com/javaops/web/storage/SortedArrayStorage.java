package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void update(Resume r) {

    }

    @Override
    protected void saveAndMove(Resume resume) {
        int index =getIndexInsert(resume);
        int i =size;
        for (; i > index; i--) {
            storage[i-1]=storage[i];
        }
        storage[index] = resume;

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
