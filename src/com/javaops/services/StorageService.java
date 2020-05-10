package com.javaops.services;

import com.javaops.config.Config;
import com.javaops.storage.SqlStorage;

import java.io.*;
import java.sql.DriverManager;
import java.util.Properties;

public class StorageService {

    public static SqlStorage getStorage() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        return Config.get().getStorage();

//        Properties props = new Properties();
//        try (InputStream is = new FileInputStream(new File("config\\resumes.properties"))) {
//            props.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));

        return new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres");
    }
}
