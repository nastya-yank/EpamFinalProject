package com.traulko.project.exception;

/**
 * The {@code DaoException} class represents dao exception.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class DaoException extends Exception {

    /**
     * Instantiates a new DaoException.
     */
    public DaoException() {
        super();
    }

    /**
     * Instantiates a new DaoException.
     *
     * @param message the message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new DaoException.
     *
     * @param throwable the throwable
     * @param message the message
     */
    public DaoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new DaoException.
     *
     * @param throwable the throwable
     */
    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
