package com.git.client.api.exception;

/**
 * IDevice not found exception.
 * <p/>
 * Date: 06.11.12
 * Time: 13:14
 *
 * @author rpleshkov
 */
public class DeviceNotFoundException extends Exception {

    /**
     * Default constructor.
     */
    public DeviceNotFoundException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
