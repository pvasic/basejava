package com.javaops.web.storage;

import com.javaops.web.model.Resume;

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
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        if (size == ARRAY_SIZE) {

            // Вне main() использовать log4j
            System.out.println("База резюме заполнена! Освободите память.");
            return;
        }
        int i = 0;
        if (size != 0) {
            for (; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {

                    // Вне main() использовать log4j
                    System.out.println("Такое резюме как: " + resume.getUuid() + " уже существует!");
                    return;
                }
            }
        }

        // В реальной DB size == 0 проверка лишняя, т.к. используется только когда DB пуста
        if ((size == 0)) {
            storage[size] = resume;
            size++;
        }
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
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }
}
