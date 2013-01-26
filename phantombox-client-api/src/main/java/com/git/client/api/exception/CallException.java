package com.git.client.api.exception;

/**
 * CallException.
 * <p/>
 * User: dmgcodevil
 * Date: 12/14/12
 * Time: 6:50 PM
 */
public class CallException extends Exception {
    /**
     * Default constructor.
     */
    public CallException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public CallException(String message) {
        super(message);
    }
}
