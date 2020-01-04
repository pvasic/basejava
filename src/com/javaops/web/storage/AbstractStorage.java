package com.javaops.web.storage;

import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.model.Resume;

import java.util.Collections;
import java.util.List;

/**
 * @author Vasichkin Pavel
 * Abstract storage based on collections for Resume
 */
public abstract class AbstractStorage<SK> implements Storage {
    protected abstract boolean isKey(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> getAll();


    @Override
    public void save(Resume resume) {
        SK searchKey = getSearchKeyIfNotExist(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume resume) {
        SK searchKey = getSearchKeyIfExist(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> arrayList = getAll();
        Collections.sort(arrayList);
        return arrayList;
    }

    private SK getSearchKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isKey(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
