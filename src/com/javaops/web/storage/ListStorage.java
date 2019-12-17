package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasichkin Pavel
 * Storage based on list for Resume
 */
public class ListStorage extends AbstractStorage {

    private static final List<Resume> storage = new ArrayList<>();

    @Override
    protected Object searchObject(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Object index, Resume resume) {
        storage.set((int) index, resume);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove((int) index);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return (storage.toArray(new Resume[0]));
    }
}
