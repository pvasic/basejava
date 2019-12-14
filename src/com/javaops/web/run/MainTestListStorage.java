package com.javaops.web.run;

import com.javaops.web.model.Resume;
import com.javaops.web.storage.ListStorage;

/**
 * @author Vasichkin Pavel
 */
public class MainTestListStorage {

    private static final ListStorage LIST_STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume r4 = new Resume("uuid4");
        Resume r2 = new Resume("uuid2");
        Resume r1 = new Resume("uuid1");

        LIST_STORAGE.save(r4);
        LIST_STORAGE.save(r2);
        LIST_STORAGE.save(r1);
        printAll();

        System.out.println("Get r1: " + LIST_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + LIST_STORAGE.size());

        //System.out.println("Get dummy: " + LIST_STORAGE.get("dummy"));

        printAll();
        Resume r3 = new Resume("uuid3");
        LIST_STORAGE.save(r3);

        printAll();
        LIST_STORAGE.update(r2);

        printAll();
        //LIST_STORAGE.save(r4);

        printAll();
        LIST_STORAGE.delete(r1.getUuid());

        printAll();
        System.out.println("Size: " + LIST_STORAGE.size());

        printAll();
        LIST_STORAGE.clear();
        printAll();

        System.out.println("Size: " + LIST_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : LIST_STORAGE.getAll()) {
            System.out.println(r);
        }
    }

}
