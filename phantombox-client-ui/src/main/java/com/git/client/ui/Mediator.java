package com.git.client.ui;

import com.git.client.api.net.ICommunication;
import com.git.client.ui.frame.LoginFrame;
import com.git.client.ui.frame.MainFrame;
import com.git.domain.api.IContact;
import com.git.domain.api.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void login(String login, String password) {
        user = communication.login(login, password, communication.getIpAddress());
        mainFrame.refreshContactsList(user.getContacts());
    }

    /**
     * Add contact by user name.
     *
     * @param userName name  of user which need to add
     */
    public void addContactByUserName(String userName) {
        user = communication.addContactByUserName(user.getName(), user.getPassword(), userName);
        mainFrame.refreshContactsList(user.getContacts());
    }


    /**
     * Add contact by email.
     *
     * @param email Email
     */
    public void addContactByContactEmail(String email) {
        user = communication.addContactByContactEmail(user.getName(), user.getPassword(), email);
        mainFrame.refreshContactsList(user.getContacts());
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
}
