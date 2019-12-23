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
        Object searchKey = getSearchKeyIfNotExist(resume.getUuid());
        saveResume(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        return getResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        deleteResume(searchKey);
    }

    private Object getSearchKeyIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isKey(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isKey(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void deleteResume(Object searchKey);
}
