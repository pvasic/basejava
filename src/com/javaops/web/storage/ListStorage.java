package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vasichkin Pavel
 * Storage based on list for Resume
 */
public class ListStorage extends AbstractStorage {

    private static final List<Resume> storage = new ArrayList<>();

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isKey(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected void doUpdate(Object index, Resume resume) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove(((Integer) index).intValue());
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
    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }
}
