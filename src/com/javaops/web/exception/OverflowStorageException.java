package com.javaops.web.exception;

/**
 * @author Vasichkin Pavel
 * Overflow storage exception
 */

public class OverflowStorageException extends StorageException {
    public OverflowStorageException(String uuid) {
        super("Stack over flow!", uuid);
    }
}
