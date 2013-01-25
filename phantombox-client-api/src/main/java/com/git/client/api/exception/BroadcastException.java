package com.git.client.api.exception;

/**
 * BroadcastException.
 * <p/>
 * Date: 1/23/13
 * Time: 11:08 PM
 *
 * @author dmgcodevil
 */
public class BroadcastException extends Exception {

    /**
     * Default constructor.
     */
    public BroadcastException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public BroadcastException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param cause {@link Throwable}
     */
    public BroadcastException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public BroadcastException(String message, Throwable cause) {
        super(message, cause);
    }
}