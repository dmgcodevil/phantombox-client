package com.git.client.api.exception;

/**
 * Data source clone exception.
 * <p/>
 * Date: 06.11.12
 * Time: 14:34
 *
 * @author rpleshkov
 */
public class DataSourceCloneException extends Exception {

    /**
     * Default constructor.
     */
    public DataSourceCloneException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public DataSourceCloneException(String message) {
        super(message);
    }
}
