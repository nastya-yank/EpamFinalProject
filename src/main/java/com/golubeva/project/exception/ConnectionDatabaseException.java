package com.golubeva.project.exception;

/**
 * The {@code ConnectionDatabaseException} class represents connection database exception.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ConnectionDatabaseException extends Exception {

    /**
     * Instantiates a new ConnectionDatabaseException.
     *
     * @param throwable the throwable
     */
    public ConnectionDatabaseException(Throwable throwable) {
        super(throwable);
    }
}
