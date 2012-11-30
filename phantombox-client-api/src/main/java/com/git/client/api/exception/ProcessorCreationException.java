package com.git.client.api.exception;

/**
 * Processor creation exception.
 * <p/>
 * Date: 06.11.12
 * Time: 15:15
 *
 * @author rpleshkov
 */
public class ProcessorCreationException extends Exception {

    /**
     * Default constructor.
     */
    public ProcessorCreationException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public ProcessorCreationException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public ProcessorCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
