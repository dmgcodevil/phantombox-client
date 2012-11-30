package com.git.client.api.exception;

/**
 * IDevice initialization exception.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 6:06 AM
 */
public class DeviceInitializationException extends Exception {

    /**
     * Default constructor.
     */
    public DeviceInitializationException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public DeviceInitializationException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     * @param cause   {@link Throwable}
     */
    public DeviceInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
