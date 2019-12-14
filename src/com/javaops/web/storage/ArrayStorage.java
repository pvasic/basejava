package com.javaops.web.storage;

import com.javaops.web.model.Resume;

/**
 * @author Vasichkin Pavel
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void removeElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Object searchObject(String uuid) {
        return null;
    }

    @Override
    protected Resume getByIndex(int index) {
        return null;
    }

    @Override
    protected boolean containsResume(Resume resume) {
        return false;
    }

    @Override
    protected void saveResume(Resume resume) {

    }

    @Override
    protected void updateResume(Object index, Resume resume) {

    }

    @Override
    protected void deleteResume(Object index) {

    }
}
