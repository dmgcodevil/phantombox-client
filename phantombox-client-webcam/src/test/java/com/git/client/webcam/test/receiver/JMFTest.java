package com.git.client.webcam.test.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JMFTest.
 * <p/>
 * User: dmgcodevil
 * Date: 11/7/12
 * Time: 10:57 AM
 */
public class JMFTest extends JFrame {

    public static final String RTP_VIDEO = "rtp://224.123.111.101:22224/video";
    public static final int WIDTH = 320;
    public static final int HEIGHT = 260;
    public static final int STATUS = 0;
    private Player player;
    private static final Logger LOGGER = LoggerFactory.getLogger(JMFTest.class);

    /**
     * Def constructor.
     */
    public JMFTest() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                player.stop();
                player.deallocate();
                player.close();
                System.exit(STATUS);
            }
        });
        //setExtent( 0, 0, 320, 260 );
        setSize(WIDTH, HEIGHT);
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());
        String mediaFile = RTP_VIDEO;
        try {
            MediaLocator mlr = new MediaLocator(mediaFile);
            LOGGER.info("create player");
            player = Manager.createRealizedPlayer(mlr);
            LOGGER.info("created player");
            if (player.getVisualComponent() != null) {
                panel.add("Center", player.getVisualComponent());
            }
            if (player.getControlPanelComponent() != null) {
                panel.add("South", player.getControlPanelComponent());
            }
        } catch (Exception e) {
            System.err.println("Got exception " + e);
        }
    }

    public static void main(String[] args) {
        JMFTest jmfTest = new JMFTest();
        jmfTest.setVisible(true);
    }
}
