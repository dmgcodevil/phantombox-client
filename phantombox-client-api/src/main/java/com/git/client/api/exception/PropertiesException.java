package com.git.client.api.exception;

/**
 * Properties exception.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 4:15 PM
 */
public class PropertiesException extends Exception {

    /**
     * Default constructor.
     */
    public PropertiesException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public PropertiesException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public PropertiesException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * Constructor with parameters.
     *
     * @param cause {@link Throwable}
     */
    public PropertiesException(Throwable cause) {
        super(cause);
    }
}
