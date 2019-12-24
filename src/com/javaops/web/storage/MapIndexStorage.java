package com.javaops.web.storage;

import com.javaops.web.model.Resume;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vasichkin Pavel
 */
public class MapIndexStorage extends AbstractStorage {
    private static final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (uuid.equals(entry.getKey())) {
                return entry.getValue().hashCode();
            }
        }
        return null;
    }

    @Override
    protected boolean isKey(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if ((Integer) searchKey == entry.getValue().hashCode()) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if ((Integer) searchKey == entry.getValue().hashCode()) {
                entry.setValue(resume);
            }
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.values().removeIf(resume -> (Integer) searchKey == resume.hashCode());
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
        return new ArrayList<>(storage.values());
    }
}
