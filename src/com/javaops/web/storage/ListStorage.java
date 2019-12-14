package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasichkin Pavel
 * Storage based on list for Resume
 */
public class ListStorage extends AbstractStorage {

    private static final List<Resume> STORAGE = new ArrayList<>();

    @Override
    protected Object searchObject(String uuid) {
        for (int i = 0; i < STORAGE.size(); i++) {
            if (uuid.equals(STORAGE.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume getResume(int index) {
        return STORAGE.get(index);
    }

    @Override
    protected boolean containsResume(Resume resume) {
        return STORAGE.contains(resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        STORAGE.add(resume);
    }

    @Override
    protected void updateResume(Object index, Resume resume) {
        STORAGE.set((int) index, resume);
    }

    @Override
    protected void deleteResume(Object index) {
        STORAGE.remove((int) index);
    }

    @Override
    public int size() {
        return STORAGE.size();
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public Resume[] getAll() {
        return (STORAGE.toArray(new Resume[0]));
    }
}
