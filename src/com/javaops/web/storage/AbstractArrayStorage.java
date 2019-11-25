package com.javaops.web.storage;

import com.javaops.web.model.Resume;

public abstract class AbstractArrayStorage {
    protected static final int STORAGE_LIMIT = 100_000;
    protected Resume[] storage;
    protected int size;
}
