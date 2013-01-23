package com.git.client.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Raman_Pliashkou
 * Date: 1/23/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class BroadcastException extends Exception {

    /**
     * Default constructor.
     */
    public BroadcastException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public BroadcastException(String message) {
        super(message);
    }
}
