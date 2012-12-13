package com.git.client.api.net;

import com.git.domain.api.IContact;
import com.git.domain.api.IUser;

/**
 * Communication interface.
 * <p/>
 * User: dmgcodevil
 * Date: 12/1/12
 * Time: 4:33 AM
 */
public interface ICommunication {

    /**
     * Login.
     *
     * @param name      user name
     * @param password  user password
     * @param ipAddress ip address
     * @return {@link IUser}
     */
    IUser login(String name, String password, String ipAddress);

    /**
     * Login.
     *
     * @param name      user name
     * @param password  user password
     * @return {@link IUser}
     */
    IUser login(String name, String password);

    /**
     * logout.
     *
     * @param name     name
     * @param password password
     * @return true - if operation complete successful
     */
    boolean logout(String name, String password);


    /**
     * Add contact by user name.
     *
     * @param name        name
     * @param password    password
     * @param contactName name  of contact which need to add
     * @return {@link IUser}
     */
    IUser addContactByName(String name, String password, String contactName);


    /**
     * Add contact by email.
     *
     * @param name     name
     * @param password password
     * @param email    Email
     * @return {@link IUser}
     */
    IUser addContactByEmail(String name, String password, String email);

    /**
     * Remove contact by id.
     *
     * @param name      name
     * @param password  password
     * @param contactId contact id
     * @return true - if operation complete successful
     */
    boolean removeContactById(String name, String password, String contactId);

    /**
     * Find contact by name.
     *
     * @param name contact name
     * @return {@link IContact}
     */
    IContact findContactByName(String name);

    /**
     * Find contact by email.
     *
     * @param email contact email
     * @return {@link IContact}
     */
    IContact findContactByEmail(String email);

    /**
     * Gets ip address.
     *
     * @return ip address
     */
    String getIpAddress();
}
