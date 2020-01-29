package com.javaops.web.exception;

/**
 * @author Vasichkin Pavel
 */
public class NotDeletedFileException extends StorageException {
    public NotDeletedFileException(String uuid) {
        super("File " + uuid + " not deleted. Perhaps it is open or protected.", uuid);
    }
}
