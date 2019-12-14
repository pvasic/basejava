package com.javaops.web.storage;

import com.javaops.web.model.Resume;

public interface Storage {

    int size();

    void clear();

    Resume get(String uuid);

    Resume[] getAll();

    void save(Resume resume);

    void update(Resume resume);

    void delete(String uuid);
}
