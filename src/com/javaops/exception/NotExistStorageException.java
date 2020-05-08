package com.javaops.exception;

/**
 * @author Vasichkin Pavel
 * Exception not existing for resume
 */

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist.", uuid);
    }
}
