package com.javaops.web.storage;

import com.javaops.web.model.Resume;

import java.util.List;

public interface Storage {

    int size();

    void clear();

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void save(Resume resume);

    void update(Resume resume);

    void delete(String uuid);
}
