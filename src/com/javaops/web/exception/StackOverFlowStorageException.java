package com.javaops.web.exception;

/**
 * @author Vasichkin Pavel
 * Stack overflow exception
 */

public class StackOverFlowStorageException extends StorageException {
    public StackOverFlowStorageException(String uuid) {
        super("Stack over flow!", uuid);
    }
}
