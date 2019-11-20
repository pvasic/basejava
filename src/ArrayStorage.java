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

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (size == ARRAY_SIZE) {
            System.out.println("База резюме заполнена! Освободите память."); // Вне main() использовать log4j
            return;
        }
        int i = 0;
        if (size != 0) {
            for (; i < size; i++) {
                if (storage[i].uuid.equals(resume.uuid)) {
                    System.out.println("Такое резюме как: " + resume.uuid + " уже существует!"); // Вне main() использовать log4j
                    return;
                }
            }
        }
        if (size == 0 || i == size) { // В реальной DB size == 0 проверка лишняя, т.к. используется только когда DB пуста
            storage[size] = resume;
            size++;
        }
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {
        if (size == 0) { // В реальной DB эта проверка лишняя, т.к. используется только когда DB пуста
            return;
        } else {
            int i = 0;
            for (; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    return;
                }
            }
            System.out.println("Удаление не возможно, такое резюме как: " + uuid + " не найдено!"); // Вне main() использовать log4j
        }
    }

    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
