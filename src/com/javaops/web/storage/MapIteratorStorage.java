package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vasichkin Pavel
 */
public class MapIteratorStorage extends AbstractStorage {
    private static final Map<String, Resume> storage = new HashMap<>();
    private static Iterator<Map.Entry<String, Resume>> iterator;

    @Override
    protected Map.Entry<String, Resume> getSearchKey(String uuid) {
        iterator = storage.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Resume> searchKey = iterator.next();
            if (uuid.equals(searchKey.getKey())) {
                return searchKey;
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
        return ((Map.Entry<String, Resume>) searchKey).getValue();
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        ((Map.Entry<String, Resume>) searchKey).setValue(resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        iterator.remove();
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
    public List<Resume> getAllSorted() {
        return storage.values().stream().sorted().collect(Collectors.toList());
    }
}
