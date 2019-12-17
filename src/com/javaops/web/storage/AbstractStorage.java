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
        Object object = searchObject(resume.getUuid());
        if (object == null) {
            saveResume(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        Object object = searchObject(uuid);
        containsResume(uuid, object);
        if (object instanceof Integer) {
            return getResume(object);
        } else {
            return getResume(uuid);
        }
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object object = searchObject(uuid);
        containsResume(uuid, object);
        if (object instanceof Integer) {
            updateResume(object, resume);
        } else {
            updateResume(uuid, resume);
        }
    }

    @Override
    public void delete(String uuid) {
        Object object = searchObject(uuid);
        containsResume(uuid, object);
        if (object instanceof Integer) {
            deleteResume(object);
        } else {
            deleteResume(uuid);
        }
    }

    private void containsResume(String uuid, Object object) {
        if (object == null) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Object searchObject(String uuid);

    protected abstract Resume getResume(Object indexOrKey);

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Object indexOrKey, Resume resume);

    protected abstract void deleteResume(Object indexOrKey);
}
