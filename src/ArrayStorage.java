import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    public ArrayStorage() {
        Arrays.stream(storage).forEach(resume -> resume = null);
    }

    void clear() {
        for (int i = 0; storage[i] != null; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {

        boolean equals = false;
        int i = 0;
        for (i = 0; storage[i] != null; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                equals = true;
            }
        }
        if (equals == false && i >= 0) {
            try {
                storage[i] = r;
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        } else {
            if (equals == true)
                System.out.println("Такое резюме как: " + r.uuid + " уже существует"); //Слелать без  System.out.println
        }
    }

    Resume get(String uuid) {

        Resume resume = null;
        for (int i = 0; storage[i] != null; i++) {
            if (storage[i].uuid.equals(uuid))
                resume = storage[i];
        }
        return resume;
    }

    void delete(String uuid) {

        boolean equals = false;
        int del = 0;
        int i;
        for (i = 0; storage[i] != null; i++) {
            if (storage[i].uuid.equals(uuid)) {
                del = i;
                equals = true;
            }
        }
        if (equals == false) {
            System.out.println("Удаление не возможно, такое резюме как: " + uuid + " не найдено"); //Не использовать кроме main System.out.println
        } else {
            storage[del] = storage[i-1];
            storage[i-1] = null;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        for (int i = 0; i < resumes.length; i++) resumes[i] = storage[i];
        return resumes;
    }

    int size() {
        int i = 0;
        for (i = 0; storage[i] != null; i++) {
        }
        return i;
    }
}
