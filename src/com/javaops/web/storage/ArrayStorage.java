package com.javaops.web.storage;
import com.javaops.web.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public ArrayStorage() {
        storage = new Resume[STORAGE_LIMIT];
    }

    public void clear() {

        // Как вариант: Arrays.fill(storage, 0, size, null);
        Arrays.stream(storage)
                .limit(size)
                .forEach(r -> r = null);
        size = 0;
        Arrays.fill(storage, 0, size, null);
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
        storage[size] = resume;
        size++;
    }

    public void update(Resume resume) {
        int i = getIndex(resume.getUuid());
        if (i > -1) {
            storage[i] = resume;
        }
        System.out.println("No resume found " + resume.getUuid() + ".");
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.println("No resume found " + uuid + ".");
            return null;
        }
        return storage[i];
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i > -1) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
        System.out.println("No resume found " + uuid + ".");
    }

    public Resume[] getAll() {

        // Как вариант: return Arrays.copyOfRange(storage, 0, size);
        return Arrays.stream(storage)
                .limit(size)
                .toArray(Resume[]::new);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
