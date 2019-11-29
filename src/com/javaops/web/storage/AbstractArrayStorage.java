package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100_000;
    public Resume[] storage;
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
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            System.out.println("Resume " + resume.getUuid() + " already exists.");
            return;
        }
        index = Math.abs(index) - 1;
        saveAndMove(resume, index);
        size++;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        }
        System.out.println("Resume " + resume.getUuid() + " not found.");
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            deleteAndMove(index);
            size--;
        }
        System.out.println("Resume " + uuid + " not found.");
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract void saveAndMove(Resume resume, int index);

    protected abstract void deleteAndMove(int index);

    protected abstract int getIndex(String uuid);
}
