package com.git.client.ui;

import com.git.client.ui.frame.LoginFrame;
import com.git.client.ui.frame.MainFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;

/**
 * Enter class description.
 * <p/>
 * Date: 06.12.12
 * Time: 17:22
 *
 * @author rpleshkov
 */
public class MainApplication {

    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext(
            new String[]{
                "/com/git/client/net/spring/phantombox-client-net-context.xml",
                "/com/git/client/ui/spring/phantombox-client-ui-context.xml"
                });
        UiMediator uiMediator = (UiMediator) appContext.getBean("UiMediator");
        final MainFrame mainFrame = new MainFrame(uiMediator);
        uiMediator.setMainFrame(mainFrame);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
