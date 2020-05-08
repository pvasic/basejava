package com.javaops.storage;

import com.javaops.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    Resume get(String uuid);

    void update(Resume resume);

    void save(Resume resume);

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();
}
