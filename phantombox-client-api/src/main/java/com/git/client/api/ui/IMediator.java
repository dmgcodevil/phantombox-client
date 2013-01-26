package com.git.client.api.ui;

import com.git.client.api.exception.CallException;
import com.git.client.api.exception.ContactException;
import com.git.client.api.exception.UserLoginException;
import com.git.domain.api.IContact;
import com.git.domain.api.IUser;

/**
 * IMediator interface.
 * <p/>
 * Date: 1/25/13
 * Time: 11:56 PM
 *
 * @author dmgcodevil
 */
public interface IMediator {

    /**
     * Gets user.
     *
     * @return user
     */
    IUser getUser();

    /**
     * Sets user.
     *
     * @param user user
     */
    void setUser(IUser user);


    /**
     * Login.
     *
     * @param login    login
     * @param password password
     * @throws UserLoginException {@link UserLoginException}
     */
    void login(String login, String password) throws UserLoginException;

    /**
     * Logout.
     *
     * @throws UserLoginException {@link UserLoginException}
     */
    void logout() throws UserLoginException;


    /**
     * Gets contact by id.
     *
     * @param contactId contact id
     * @return contact
     */
    IContact getContactById(String contactId);

    /**
     * Add contact by  name.
     *
     * @param name name of contact which need to add
     * @return {@link IContact}
     * @throws UserLoginException {@link UserLoginException}
     * @throws ContactException   {@link ContactException}
     */
    IContact addContactByName(String name) throws UserLoginException, ContactException;

    /**
     * Add contact by email.
     *
     * @param email Email
     * @return {@link IContact}
     * @throws UserLoginException {@link UserLoginException}
     * @throws ContactException   {@link ContactException}
     */
    IContact addContactByEmail(String email) throws UserLoginException, ContactException;

    /**
     * Remove contact.
     *
     * @param contact {@link IContact}
     * @return true - if operation complete successful
     */
    boolean removeContact(IContact contact);


    /**
     * Call.
     *
     * @param contact contact {@link IContact}
     * @throws CallException {@link ContactException}
     */
    void call(final IContact contact) throws CallException;

    /**
     * Creates call frame.
     *
     * @param rtpVideo rtp video
     * @param rtpAudio rtp audio
     * @param contact  contact
     */
    void createCallFrame(String rtpVideo, String rtpAudio, IContact contact);

    /**
     * Dispose call frame.
     *
     * @param contact contact
     */
    void disposeCallFrame(IContact contact);


    /**
     * Stop the call.
     *
     * @param contact contact
     * @throws CallException {@link ContactException}
     */
    void stopCall(final IContact contact) throws CallException;

}
