package com.javaops.services;

import com.javaops.storage.SqlStorage;

public class StorageService {

    public static SqlStorage getStorage() {
        return new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres");
    }
}
