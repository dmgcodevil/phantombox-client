package com.git.client.net;

import com.git.client.api.net.ICommunication;
import com.git.domain.api.IContact;
import com.git.domain.api.IUser;
import com.git.server.rest.call.RestContactServiceCaller;
import com.git.server.rest.call.RestUserServiceCaller;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    @Autowired
    private RestContactServiceCaller restContactServiceCaller;

    private static final Logger LOGGER = Logger.getLogger(Communication.class);

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
    public IUser addContactByName(String name, String password, String userName) {
        return restUserServiceCaller.addContactByName(name, password, userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser addContactByEmail(String name, String password, String email) {
        return restUserServiceCaller.addContactByContactEmail(name, password, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeContactById(String name, String password, String contactId) {
        return restUserServiceCaller.removeContactById(name, password, contactId);
    }

    @Override
    public IContact findContactByName(String name) {
        return restContactServiceCaller.findContactByName(name);
    }

    @Override
    public IContact findContactByEmail(String email) {
        return restContactServiceCaller.findContactByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIpAddress() {
//        String ipAddress = StringUtils.EMPTY;
//        URL remoteUrl = null;
//        BufferedReader in = null;
//        try {
//            remoteUrl = new URL("http://automation.whatismyip.com/n09230945.asp");
//            in = new BufferedReader(new InputStreamReader(remoteUrl.openStream()));
//            ipAddress = in.readLine();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            LOGGER.error(ExceptionUtils.getMessage(e));
//        } catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getMessage(e));
//        }
//        return ipAddress;
        String ipAddress = StringUtils.EMPTY;
        BufferedReader in = null;
        try {
        URL myIP = new URL("http://api.externalip.net/ip/");
         in = new BufferedReader(
            new InputStreamReader(myIP.openStream())
        );
            ipAddress=  in.readLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LOGGER.error(ExceptionUtils.getMessage(e));
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
        }

         return ipAddress;
    }
}
