package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int ARRAY_SIZE = 10000;
    Resume[] storage;
    int size;

    public ArrayStorage() {
        this.storage = new Resume[ARRAY_SIZE];
        this.size = 0;
    }

    public void clear() {
        Arrays.stream(storage)
                .limit(size)
                .forEach(r -> r = null);
    }

    // Лучше сравнивать обьект Resume с помощью интерфейса Comparator
    //
    // Сравнивал uuid вместо resume, чтобы не создавать новый обьект Resume в методе get() и delete()
    public int contains(String uuid) {
        int i = 0;
        for (; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {

                // Вне main() использовать log4j
                System.out.println("Такое резюме как: com.javaops.web.model.Resume " + uuid + " существует!");
                return i;
            }
        }
        return -1;
    }

    public void save(Resume resume) {
        if (size == ARRAY_SIZE) {

            // Вне main() использовать log4j
            System.out.println("База резюме заполнена! Освободите память.");
            return;
        }
        if (contains(resume.getUuid()) > -1) {
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
    }

    public Resume get(String uuid) {
        int i = contains(uuid);
        if (i == -1) {
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
    }

    public Resume[] getAll() {
        return Arrays.stream(storage)
                .limit(size)
                .toArray(Resume[]::new);
    }

    public int size() {
        return size;
    }
}
