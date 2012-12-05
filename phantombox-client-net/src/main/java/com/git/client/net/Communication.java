package com.git.client.net;

import com.git.client.api.net.ICommunication;
import com.git.domain.api.IUser;
import com.git.server.rest.call.RestUserServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@link ICommunication} interface implementation.
 * <p/>
 * Date: 05.12.12
 * Time: 17:27
 *
 * @author rpleshkov
 */
public class Communication implements ICommunication {

    @Autowired
    private RestUserServiceCaller restUserServiceCaller;

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser login(String name, String password, String ipAddress) {
        return restUserServiceCaller.login(name, password, ipAddress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean logout(String name, String password) {
        return restUserServiceCaller.logout(name, password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser addContactByUserName(String name, String password, String userName) {
        return restUserServiceCaller.addContactByUserName(name, password, userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser addContactByContactEmail(String name, String password, String email) {
        return restUserServiceCaller.addContactByContactEmail(name, password, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeContactById(String name, String password, String contactId) {
        return restUserServiceCaller.removeContactById(name, password, contactId);
    }
}
