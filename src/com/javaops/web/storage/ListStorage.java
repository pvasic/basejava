package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Vasichkin Pavel
 */
public class ListStorage extends AbstractStorage {

    private static final List<Resume> STORAGE = new ArrayList();

    @Override
    protected Object searchObject(String uuid) {
            Resume resume = new Resume(uuid);
            return STORAGE.indexOf(resume);
    }

    @Override
    protected Resume getByIndex(int index) {
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
        STORAGE.set((Integer) index, resume);
    }

    @Override
    protected void deleteResume(Object index) {
        STORAGE.remove((Integer) index);
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
        return (Resume[]) STORAGE.toArray();
    }
}
