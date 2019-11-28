package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100_000;
    protected Resume[] storage;
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.println("No resume found " + uuid + ".");
            return null;
        }
        return storage[i];
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("The database is full! Free memory.");
            return;
        }
        if (getIndex(resume.getUuid()) > -1) {
            System.out.println("Resume " + resume.getUuid() + " already exists.");
            return;
        }
        saveAndMove(resume);
        size++;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            deleteAndMove(index);
            size--;
        }
        System.out.println("No resume found " + uuid + ".");
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected int getIndexInsert(Resume resume) {
        return Arrays.binarySearch(storage, 0, size, resume, Resume::compareTo);
    }

    protected abstract void saveAndMove(Resume resume);
    protected abstract void deleteAndMove(int index);

    protected abstract int getIndex(String uuid);
}
