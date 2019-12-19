package com.javaops.web.storage;

import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.model.Resume;

/**
 * @author Vasichkin Pavel
 * Abstract storage based on collections for Resume
 */
public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object key = getSearchKeyIfNotExist(resume.getUuid());
        saveResume(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getSearchKeyIfExist(uuid);
        return getResume(key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getSearchKeyIfExist(resume.getUuid());
        updateResume(key, resume);
    }

    @Override
    public void delete(String uuid) {
        Object key = getSearchKeyIfExist(uuid);
        deleteResume(key);
    }

    protected Object getSearchKeyIfExist(String uuid) {
        Object key = searchKey(uuid);
        if (!isKey(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected Object getSearchKeyIfNotExist(String uuid) {
        Object key = searchKey(uuid);
        if (isKey(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isKey(Object key);

    protected abstract Object searchKey(String uuid);

    protected abstract Resume getResume(Object key);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Object key, Resume resume);

    protected abstract void deleteResume(Object key);
}
