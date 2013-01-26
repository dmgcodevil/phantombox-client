package com.git.client.ui;

import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.ISelectorBuilder;
import com.git.broker.api.domain.SelectorCondition;
import com.git.broker.impl.domain.SelectorBuilder;
import com.git.client.api.exception.CallException;
import com.git.client.api.exception.ContactException;
import com.git.client.api.exception.UserLoginException;
import com.git.client.api.net.ICommunication;
import com.git.client.api.ui.IMediator;
import com.git.client.api.ui.ISwingJmsExchanger;
import com.git.client.ui.frame.CallFrame;
import com.git.client.ui.frame.LoginFrame;
import com.git.client.ui.frame.MainFrame;
import com.git.client.ui.util.CallFrameManager;
import com.git.domain.api.IContact;
import com.git.domain.api.IUser;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;


/**
 * Mediator.
 * <p/>
 * User: dmgcodevil
 * Date: 12/6/12
 * Time: 7:26 PM
 */
@Component("mediator")
public class Mediator implements IMediator {

    private static final int PORT = 0;
    @Autowired
    private ICommunication communication;

    @Autowired
    private ISwingJmsExchanger jmsExchanger;

    private ISelectorBuilder selectorBuilder = new SelectorBuilder();

    private IUser user;

    private MainFrame mainFrame;

    private LoginFrame loginFrame;

    private CallFrameManager callFrameManager = new CallFrameManager();

    private static final Logger LOGGER = LoggerFactory.getLogger(Mediator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getUser() {
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUser(IUser user) {
        this.user = user;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void login(String login, String password) throws UserLoginException {
        LOGGER.info("login():: login={}, password={}", login, password);
        user = communication.login(login, password);
        if (user != null && user.getContact().isOnline()) {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(PORT);
            } catch (IOException e) {
                LOGGER.error("failed generate port");
                throw new UserLoginException("failed generate port", e);
            }
            user.getContact().getConnection().setVideoPort(serverSocket.getLocalPort());
            user.getContact().getConnection().setAudioPort(serverSocket.getLocalPort());
            // caution: build selector
            LOGGER.info("CAUTION: build selector: {}={}", CONTACT_ID_PROPERTY, user.getContact().getId());
            jmsExchanger.setContact(user.getContact());
            jmsExchanger.setSelector(selectorBuilder.createSelector()
                .addProperty(CONTACT_ID_PROPERTY)
                .addCondition(SelectorCondition.EQUALS)
                .addPropertyValue(user.getContact().getId())
                .buildSelector());
            mainFrame.refreshContactsList(user.getContacts());
        } else {
            throw new UserLoginException("You are not authorized. Try again.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout() throws UserLoginException {
        if (checkAuthorized()) {
            boolean res = communication.logout(user.getName(), user.getPassword());
            if (res) {
                mainFrame.refreshContactsList(Collections.<IContact>emptySet());
            }
        } else {
            throw new UserLoginException("You are not authorized. Please login.");
        }
    }

    @Override
    public IContact getContactById(String contactId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IContact addContactByName(String name) throws UserLoginException, ContactException {
        IContact contact = null;
        if (checkAuthorized()) {
            contact = communication.findContactByName(name);
            if (contact != null) {
                user = communication.addContactByName(user.getName(), user.getPassword(), contact.getName());
                mainFrame.refreshContactsList(user.getContacts());
                return contact;
            }
            throw new ContactException("Contact not found.");
        } else {
            throw new UserLoginException("You are not authorized. Please login.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IContact addContactByEmail(String email) throws UserLoginException, ContactException {
        IContact contact = null;
        if (checkAuthorized()) {
            contact = communication.findContactByEmail(email);
            if (contact != null) {
                user = communication.addContactByEmail(user.getName(), user.getPassword(), contact.getEmail());
                mainFrame.refreshContactsList(user.getContacts());
                return contact;
            }
            throw new ContactException("Contact not found.");
        } else {
            throw new UserLoginException("You are not authorized. Please login.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeContact(IContact contact) {
        boolean removed = false;
        if (user != null) {
            removed = communication.removeContactById(user.getName(), user.getPassword(),
                contact.getId());
            if (removed) {
                //user = communication.login(login, password, communication.getIpAddress());
                user.getContacts().remove(contact);
                mainFrame.refreshContactsList(user.getContacts());
            }
        }
        return removed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(final IContact contact) throws CallException {
        IContact repContact = communication.findContactByName(contact.getName());
        if (repContact != null && repContact.isOnline()) {
            Thread callThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    jmsExchanger.call(user.getContact(), contact);
                }
            });
            callThread.start();
        } else {
            throw new CallException("Contact '" + contact.getName() + "' offline");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCallFrame(String rtpVideo, String rtpAudio, IContact contact) {
        CallFrame callFrame = new CallFrame(rtpVideo, rtpAudio, contact, this);
        callFrameManager.add(contact, callFrame);
        callFrame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disposeCallFrame(IContact contact) {
        CallFrame callFrame = callFrameManager.remove(contact);
        callFrame.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopCall(final IContact contact) throws CallException {
        IContact repContact = communication.findContactByName(contact.getName());
        if (repContact != null && repContact.isOnline()) {
            Thread callThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    jmsExchanger.stopCall(user.getContact(), contact);
                }
            });
            callThread.start();
        } else {
            throw new CallException("contact {" + contact.getName() + "} not exist or offline");
        }
    }

    private boolean checkAuthorized() {
        boolean authorized = false;
        if (user != null && user.getContact().isOnline()) {
            authorized = true;
        }
        return authorized;
    }
}
