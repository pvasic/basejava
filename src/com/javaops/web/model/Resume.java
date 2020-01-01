package com.javaops.web.model;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Vasichkin Pavel
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private String uuid;
    private String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        this.uuid = uuid;
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((uuid == null) ? 0 : uuid.hashCode());
        result = 37 * result + ((fullName == null) ? 0 : fullName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume resume) {
        int result = fullName.compareTo(resume.fullName);
        if (result == 0) {
            return uuid.compareTo(resume.uuid);
        } else {
            return result;
        }
    }
}
