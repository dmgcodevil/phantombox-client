package com.git.client.api.exception;

/**
 * UserLoginException.
 * <p/>
 * User: dmgcodevil
 * Date: 12/7/12
 * Time: 3:35 PM
 */
public class UserLoginException extends Exception {

    /**
     * Default constructor.
     */
    public UserLoginException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public UserLoginException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public UserLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
