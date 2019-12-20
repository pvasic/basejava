package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vasichkin Pavel
 */
public class MapStorage extends AbstractStorage {
    private static final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object searchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        } else {
            return null;
        }
    }

    @Override
    protected boolean isKey(Object key) {
        return key != null;
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get(key);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.put((String) key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(key);
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
        return storage.values().toArray(new Resume[0]);
    }
}
