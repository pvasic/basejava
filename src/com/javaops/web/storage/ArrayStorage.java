package com.javaops.web.storage;
import com.javaops.web.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public ArrayStorage() {
        storage = new Resume[STORAGE_LIMIT];
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        }
        System.out.println("No resume found " + resume.getUuid() + ".");
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
    protected void saveAndMove(Resume resume) {
        storage[size]=resume;
    }

    @Override
    protected void deleteAndMove(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}
