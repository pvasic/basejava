package com.javaops.storage;

import com.javaops.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Vasichkin Pavel
 * Array sorted storage for Resumes
 */

public class SortedArrayStorage extends AbstractArrayStorage {

//    private static class ResumeComparator implements Comparator<Resume> {
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            int cmp = o1.getFullName().compareTo(o2.getFullName());
//            return cmp != 0 ? cmp : o1.getUuid().compareTo(o2.getUuid());
//        }
//    }

//    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getUuid().compareTo(o2.getUuid());
//        }
//    };

//    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

//    private static final Comparator<Resume> RESUME_COMPARATORR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected void insertElement(Resume resume, int index) {
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected void removeElement(int index) {
        int numberMove = size - index - 1;
        if (numberMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberMove);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "FullName");
        return Arrays.binarySearch(storage, 0, size, searchKey, Comparator.comparing(Resume::getUuid));
    }
}
