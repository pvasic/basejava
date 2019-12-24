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
        this.uuid = uuid;
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
        return Objects.equals(getUuid(), resume.getUuid()) && Objects.equals(getFullName(), resume.getFullName());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = 37 * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getUuid();
    }

    @Override
    public int compareTo(Resume resume) {
        return getFullName().compareTo(resume.getFullName());
    }
}
