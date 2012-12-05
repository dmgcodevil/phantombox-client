package com.git.client.api.net;

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
     * @param name     name
     * @param password password
     * @param userName name  of user which need to add
     * @return {@link IUser}
     */
    IUser addContactByUserName(String name, String password, String userName);


    /**
     * Add contact by email.
     *
     * @param name     name
     * @param password password
     * @param email    Email
     * @return {@link IUser}
     */
    IUser addContactByContactEmail(String name, String password, String email);

    /**
     * Remove contact by id.
     *
     * @param name      name
     * @param password  password
     * @param contactId contact id
     * @return true - if operation complete successful
     */
    boolean removeContactById(String name, String password, String contactId);
}
