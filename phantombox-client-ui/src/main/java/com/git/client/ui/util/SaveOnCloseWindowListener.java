package com.git.client.ui.util;

import com.git.client.api.exception.UserLoginException;
import com.git.client.ui.Mediator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * SaveOnCloseWindowListener.
 * <p/>
 * User: dmgcodevil
 * Date: 12/12/12
 * Time: 6:00 PM
 */
public class SaveOnCloseWindowListener extends WindowAdapter {
    private Mediator mediator;
    private JFrame frame;

    public SaveOnCloseWindowListener(Mediator mediator, JFrame frame) {
        this.frame = frame;
        this.mediator = mediator;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            mediator.logout();
        } catch (UserLoginException ex) {
            ex.printStackTrace();
        }
        frame.dispose();
    }
}