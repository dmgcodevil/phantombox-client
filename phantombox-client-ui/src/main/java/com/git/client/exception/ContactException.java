package com.git.client.exception;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/8/12
 * Time: 10:47 AM
 */
public class ContactException extends Exception {

    /**
     * Default constructor.
     */
    public ContactException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message message
     */
    public ContactException(String message) {
        super(message);
    }
}
