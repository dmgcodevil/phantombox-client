package com.git.client.ui;

import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.ISelectorBuilder;
import com.git.broker.api.domain.SelectorCondition;
import com.git.broker.impl.domain.SelectorBuilder;
import com.git.client.api.net.ICommunication;
import com.git.client.exception.CallException;
import com.git.client.exception.ContactException;
import com.git.client.exception.UserLoginException;
import com.git.client.ui.frame.LoginFrame;
import com.git.client.ui.frame.MainFrame;
import com.git.domain.api.IContact;
import com.git.domain.api.IUser;
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
public class Mediator {

    @Autowired
    private ICommunication communication;

    @Autowired
    private IJmsExchanger jmsExchanger;

    private ISelectorBuilder selectorBuilder = new SelectorBuilder();

    private IUser user;

    private MainFrame mainFrame;

    private LoginFrame loginFrame;

    public IUser getUser() {
        return user;
    }

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
     * Login.
     *
     * @param login    login
     * @param password password
     */
    public void login(String login, String password) throws UserLoginException {
        user = communication.login(login, password);
        if (user != null && user.getContact().isOnline()) {
            ServerSocket s = null;
            try {
                s = new ServerSocket(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.getContact().getConnection().setVideoPort(s.getLocalPort());
            user.getContact().getConnection().setAudioPort(s.getLocalPort());
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
     * Logout.
     */
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


    /**
     * Add contact by  name.
     *
     * @param name name of contact which need to add
     * @return {@link IContact}
     */
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
     * Add contact by email.
     *
     * @param email Email
     * @return {@link IContact}
     */
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
     * Remove contact.
     *
     * @param contact {@link IContact}
     * @return true - if operation complete successful
     */
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

    private boolean checkAuthorized() {
        boolean authorized = false;
        if (user != null && user.getContact().isOnline()) {
            authorized = true;
        }
        return authorized;
    }
}
