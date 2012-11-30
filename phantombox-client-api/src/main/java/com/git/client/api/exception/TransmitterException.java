package com.git.client.api.exception;

/**
 * Transmitter exception.
 * <p/>
 * Date: 06.11.12
 * Time: 15:58
 *
 * @author rpleshkov
 */
public class TransmitterException extends Exception {

    /**
     * Default constructor.
     */
    public TransmitterException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public TransmitterException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public TransmitterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters.
     *
     * @param cause {@link Throwable}
     */
    public TransmitterException(Throwable cause) {
        super(cause);
    }
}
