package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume resume, int index) {
        System.arraycopy(storage, index, storage, index + 1, size-index);
        storage[index] = resume;
    }

    @Override
    protected void removeElement(int index) {
        int numberMove = size - index -1;
        if (numberMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberMove);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
