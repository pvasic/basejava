package com.javaops.web.exception;

/**
 * @author Vasichkin Pavel
 * Exception based for resume
 */

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}