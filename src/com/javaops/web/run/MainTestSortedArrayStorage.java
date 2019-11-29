package com.javaops.web.run;

import com.javaops.web.model.Resume;
import com.javaops.web.storage.SortedArrayStorage;

public class MainTestSortedArrayStorage {
    private static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r1 = new Resume();
        r1.setUuid("uuid1");

        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r1);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        ARRAY_STORAGE.save(r3);

        printAll();
        ARRAY_STORAGE.update(r2);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());

        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
