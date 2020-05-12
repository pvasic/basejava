package com.javaops.services;

import com.javaops.config.Configuration;
import com.javaops.storage.SqlStorage;

public class StorageService {

    public static SqlStorage getStorage() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        return Configuration.get().getStorage();

//        Properties props = new Properties();
//        try (InputStream is = Config.class.getResourceAsStream(Paths.get("config\\resumes.properties").toAbsolutePath().toString()) {
//            props.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));

        return new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres");
    }
}
