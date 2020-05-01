package com.javaops.web.run;

import com.javaops.web.model.Resume;

import java.util.*;

/**
 * @author Vasichkin Pavel
 */
public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "FullName");

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "FullName");

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "FullName");

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "FullName");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        for (Resume r : collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                //collection.remove(r);
            }
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());


        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        // Bad!
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        // Arrays.asList вернет неизменяемый ArrayList, так как этот обект является обьектом вложенного (Nested, static) клсса в классе Arrays . Тема Nested class.
        // UnsupportedOperationException
        //resumes.remove(1);
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);

        System.out.println(resumes);

        String s3 = "hello";
        String s1 = "hello";
        String s2 = "hell";

        List<String> strings = new ArrayList<>();
        strings.add(s1);
        strings.add(s2);
        strings.add(s3);
        Collections.sort(strings, Comparator.comparing(String::new));
        for (String s : strings) {
            System.out.println(s);
        }

    }
}
