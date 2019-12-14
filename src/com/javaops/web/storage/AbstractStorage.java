package com.javaops.web.storage;

import com.javaops.web.exception.ExistStorageException;
import com.javaops.web.exception.NotExistStorageException;
import com.javaops.web.model.Resume;

/**
 * @author Vasichkin Pavel
 */
public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        Object object = searchObject(uuid);
        if (object instanceof Integer) {
            int index = (int) object;
            if (index >= 0) {
                return getByIndex(index);
            } else {
                if (index<0 || object == null) {
                    throw new NotExistStorageException(uuid);
                }
            }
        }
        return (Resume) object;
    }

    @Override
    public void save(Resume resume) {
        if (containsResume(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume);
        }
    }

    @Override
    public void update(Resume resume) {
        Object object = searchObject(resume.getUuid());
        if (object instanceof Integer) {
            int index = (Integer) object;
            if (index >= 0) {
                updateResume(index, resume);
                return;
            } else {
                if (index<0 || object == null) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
        }
        updateResume(resume.getUuid(), resume);
    }

    @Override
    public void delete(String uuid) {
        Object object = searchObject(uuid);
        if (object instanceof Integer) {
            int index = (int) object;
            if (index >= 0) {
                deleteResume(index);
                return;
            } else {
                if (index<0 || object == null) {
                    throw new NotExistStorageException(uuid);
                }
            }
        }
        deleteResume(uuid);
    }

    protected abstract Object searchObject(String uuid);

    protected abstract Resume getByIndex(int index);

    protected abstract boolean containsResume(Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Object index, Resume resume);

    protected abstract void deleteResume(Object index);

    public abstract int size();

    public abstract void clear();
}
