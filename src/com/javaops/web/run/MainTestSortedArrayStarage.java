package com.javaops.web.run;

import com.javaops.web.model.Resume;
import com.javaops.web.storage.SortedArrayStorage;

public class MainTestSortedArrayStarage {
    private static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid17");
        Resume r2 = new Resume();
        r2.setUuid("uuid9");
        Resume r3 = new Resume();
        r3.setUuid("uuid46");


        ARRAY_STORAGE.storage[0] = r1;

        //ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        Resume r4 = new Resume();
        r4.setUuid("uuid28");
        ARRAY_STORAGE.update(r4);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
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
