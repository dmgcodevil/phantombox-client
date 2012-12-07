package com.git.client.ui;

import com.git.client.api.net.ICommunication;
import com.git.client.ui.frame.LoginFrame;
import com.git.client.ui.frame.MainFrame;
import com.git.domain.api.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/6/12
 * Time: 7:26 PM
 */
@Component("UiMediator")
public class UiMediator {

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

    public void login(String login, String password) {
        user = communication.login(login, password, communication.getIpAddress());
        mainFrame.refreshContactsList(user.getContacts());
    }
}
