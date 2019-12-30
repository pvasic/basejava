package com.javaops.web.storage;

import com.javaops.web.model.Resume;


import java.util.*;

/**
 * @author Vasichkin Pavel
 */
public class MapIndexStorage extends AbstractStorage {
    private static final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        int index = 0;
        for (String key: storage.keySet()) {
            if (uuid.equals(key)) {
                return index;
            }
            index++;
        }
        return null;
    }

    @Override
    protected boolean isKey(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Iterator<Resume> iter = getIterByIndex((Integer) searchKey);
         Resume resume = iter.next();
        return resume;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        doDelete(searchKey);
        doSave(resume, searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        Iterator<Resume> iter = getIterByIndex((Integer) searchKey);
        iter.next();
        iter.remove();
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

    private Iterator<Resume> getIterByIndex(int index){
        Iterator<Resume> iter = storage.values().iterator();
        for (int i = 0; i < index && iter.hasNext() ; i++) {
            iter.next();
        }
        return iter;
    }
}
