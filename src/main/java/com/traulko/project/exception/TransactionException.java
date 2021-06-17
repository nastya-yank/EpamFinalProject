package com.traulko.project.exception;

/**
 * The {@code TransactionException} class represents transaction exception.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class TransactionException extends Exception {

    /**
     * Instantiates a new TransactionException.
     *
     * @param message the message
     * @param throwable the throwable
     */
    public TransactionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
