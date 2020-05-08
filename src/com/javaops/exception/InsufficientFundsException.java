package com.javaops.exception;

/**
 * @author Vasichkin Pavel
 */
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
