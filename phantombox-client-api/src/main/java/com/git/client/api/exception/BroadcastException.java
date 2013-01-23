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

    public BroadcastException(String message, Throwable cause) {
        super(message, cause);
    }
}