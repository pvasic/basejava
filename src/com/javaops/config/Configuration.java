package com.javaops.config;

import com.javaops.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Configuration INSTANCE = new Configuration();

    private final File storageDir;
    private final SqlStorage storage;

    public static Configuration get() {
        return INSTANCE;
    }

    private Configuration() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public SqlStorage getStorage() {
        return storage;
    }
}