package com.git.client.api.exception;

/**
 * Data source creation exception.
 * <p/>
 * Date: 06.11.12
 * Time: 16:38
 *
 * @author rpleshkov
 */
public class DataSourceCreationException extends Exception {

    /**
     * Default constructor.
     */
    public DataSourceCreationException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public DataSourceCreationException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public DataSourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
