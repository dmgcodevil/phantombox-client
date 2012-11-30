package com.git.client.api.exception;

/**
 * Media locator creation exception.
 * <p/>
 * Date: 08.11.12
 * Time: 15:34
 *
 * @author rpleshkov
 */
public class MediaLocatorCreationException extends Exception {

    /**
     * Default constructor.
     */
    public MediaLocatorCreationException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public MediaLocatorCreationException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public MediaLocatorCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
