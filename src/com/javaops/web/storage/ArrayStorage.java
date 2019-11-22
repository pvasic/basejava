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

    public boolean contains(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {

                // Вне main() использовать log4j
                System.out.println("Такое резюме как: " + resume.getUuid() + " уже существует!");
                return true;
            }
        }
        return false;
    }

    public void save(Resume resume) {
        if (size == ARRAY_SIZE) {

            // Вне main() использовать log4j
            System.out.println("База резюме заполнена! Освободите память.");
            return;
        }
        if (contains(resume)) {
            return;
        }
        storage[size] = resume;
        size++;
    }

    public void Update(Resume resume) {

    }

    public Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    public void delete(String uuid) {

        // В реальной DB эта проверка лишняя, т.к. используется только когда DB пуста
        if (size == 0) {
            return;
        } else {
            int i = 0;
            for (; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    return;
                }
            }

            // Вне main() использовать log4j
            System.out.println("Удаление не возможно, такое резюме как: " + uuid + " не найдено!");
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
