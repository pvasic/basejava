package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Vasichkin Pavel
 * Array sorted storage for Resumes
 */

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
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "FullName");
        return Arrays.binarySearch(storage, 0, size, searchKey, Comparator.comparing(Resume::getUuid));
    }
}
