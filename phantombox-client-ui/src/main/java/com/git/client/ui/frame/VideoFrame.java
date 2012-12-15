package com.git.client.ui.frame;

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
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 7:27 PM
 */
public class VideoFrame extends JFrame {

    public String rtpVideo;
    public static final int WIDTH = 320;
    public static final int HEIGHT = 260;
    public static final int STATUS = 0;
    private Player player;
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoFrame.class);

    public VideoFrame(String rtpVideo) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rtpVideo = rtpVideo;
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
        String mediaFile = rtpVideo;
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
}
