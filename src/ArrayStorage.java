import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int MASS_SIZE = 10000;
    Resume[] storage;
    int rsize;

    public ArrayStorage() {
        this.storage = new Resume[MASS_SIZE];
        this.rsize = 0;
    }

    void clear() {
        for (int i = 0; i < rsize; i++) {
            storage[i] = null;
        }
        rsize = 0;
    }

    void save(Resume r) {
        boolean equals = false;
        int i = 0;
        for (i = 0; i < rsize; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                equals = true;
            }
        }
        if (equals == false && i < MASS_SIZE) {
            storage[i] = r;
            rsize++;
        } else {
            if (equals == true)
                System.out.println("Такое резюме как: " + r.uuid + " уже существует!"); // Вне main() использовать log4j
            else {
                if (equals == false && i >= MASS_SIZE)
                    System.out.println("База резюме заполнена! Освободите память."); // Вне main() использовать log4j
            }
        }
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < rsize; i++) {
            if (storage[i].uuid.equals(uuid))
                resume = storage[i];
        }
        return resume;
    }

    void delete(String uuid) {
        boolean equals = false;
        int del = 0;
        int i;
        for (i = 0; i < rsize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                del = i;
                equals = true;
            }
        }
        if (equals == false) {
            System.out.println("Удаление не возможно, такое резюме как: " + uuid + " не найдено!"); // Вне main() использовать log4j
        } else {
            storage[del] = storage[i - 1];
            storage[i - 1] = null;
            rsize--;
        }
    }

    Resume[] getAll() {
        Resume[] resumes = new Resume[rsize];
        for (int i = 0; i < rsize; i++)
            resumes[i] = storage[i];
        return resumes;
    }

    int size() {
        return rsize;
    }
}
