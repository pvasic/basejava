package com.javaops.web.storage;
import com.javaops.web.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int ARRAY_SIZE = 10_000;
    private Resume[] storage;
    private int size;

    public ArrayStorage() {
        storage = new Resume[ARRAY_SIZE];
    }

    public void clear() {
        Arrays.stream(storage)
                .limit(size)
                .forEach(r -> r = null);
    }

    public void save(Resume resume) {
        if (size == ARRAY_SIZE) {
            System.out.println("The database is full! Free memory.");
            return;
        }
        if (contains(resume.getUuid()) > -1) {
            System.out.println("Resume " + resume.getUuid() + " already exists.");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public void update(Resume resume) {
        int i = contains(resume.getUuid());
        if (i > -1) {
            storage[i] = resume;
        }
        System.out.println("No resume found " + resume.getUuid() + ".");
    }

    public Resume get(String uuid) {
        int i = contains(uuid);
        if (i == -1) {
            System.out.println("No resume found " + uuid + ".");
            return null;
        }
        return storage[i];
    }

    public void delete(String uuid) {
        int i = contains(uuid);
        if (i > -1) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
        System.out.println("No resume found " + uuid + ".");
    }

    public Resume[] getAll() {
        return Arrays.stream(storage)
                .limit(size)
                .toArray(Resume[]::new);
    }

    public int size() {
        return size;
    }

    private int contains(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
