package com.javaops.config;

import com.javaops.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private final File storageDir;
    private final SqlStorage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        System.out.println(PROPS.getAbsolutePath());
        System.out.println(Config.class.getResource(PROPS.getAbsolutePath()));
        System.out.println(Config.class.getResourceAsStream(PROPS.getAbsolutePath()));
        System.out.println(getClass().getClassLoader().getResource(PROPS.getAbsolutePath()));

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