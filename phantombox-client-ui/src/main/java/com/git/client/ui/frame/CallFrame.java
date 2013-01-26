package com.git.client.ui.frame;

import com.git.client.api.exception.CallException;
import com.git.client.api.ui.IMediator;
import com.git.domain.api.IContact;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * CallFrame.
 */
public class CallFrame extends JFrame {

    private JPanel contentPane;

    private Player videoPlayer;

    private Player audioPlayer;

    private IContact contact;

    private IMediator mediator;

    private JPanel videoPanel;

    private JPanel audioPanel;

    private JPanel navPanel;

    private JButton button;

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoFrame.class);

    public CallFrame(String rtpVideo, String rtpAudio, IContact contact, IMediator mediator) {
        this.contact = contact;
        this.mediator = mediator;
        init();
        prepareVideoPlayer(rtpVideo);
        alignment(this);
        setResizable(false);
    }

    private void init() {
        buildTitle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 394, 374);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("default:grow"),
            FormFactory.RELATED_GAP_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(148dlu;default)"),
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(31dlu;default):grow"),
                FormFactory.RELATED_GAP_ROWSPEC,}));

        videoPanel = new JPanel();
        videoPanel.setLayout(new BorderLayout());
        contentPane.add(videoPanel, "2, 2, fill, fill");

        navPanel = new JPanel();
        contentPane.add(navPanel, "2, 4, fill, fill");


        java.net.URL callImageURL = CallFrame.class.getResource("/com/git/client/ui/ico/cancel.png");
        button = new JButton(new ImageIcon(callImageURL));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mediator.stopCall(contact);
                } catch (CallException ex) {
                    LOGGER.error("------ STOP CALL IS FAILED ------");
                    LOGGER.error(ExceptionUtils.getMessage(ex));
                }
            }
        });
        navPanel.add(button);
    }

    @Override
    public void dispose() {
        if (videoPlayer != null) {
            LOGGER.info("stop videoPlayer");
            videoPlayer.stop();
        }

        if (audioPlayer != null) {
            LOGGER.info("stop audioPlayer");
            audioPlayer.stop();
        }
        super.dispose();
    }

    private void alignment(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int fWidth = frame.getSize().width;
        int fHeight = frame.getSize().height;
        int x = (dim.width - fWidth) / 2;
        int y = (dim.height - fHeight) / 2;
        frame.setLocation(x, y);
    }

    private void prepareVideoPlayer(String rtpVideo) {
        try {
            MediaLocator mlr = new MediaLocator(rtpVideo);
            LOGGER.info("create video player: {}", rtpVideo);
            videoPlayer = Manager.createRealizedPlayer(mlr);

            videoPlayer.stop();

            if (videoPlayer.getVisualComponent() != null) {
                videoPanel.add("Center", videoPlayer.getVisualComponent());
            }
            if (videoPlayer.getControlPanelComponent() != null) {
                videoPanel.add("South", videoPlayer.getControlPanelComponent());
            }
        } catch (Exception e) {
            LOGGER.error("Failed create video player: {}", rtpVideo);
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
    }

    private void prepareAudioPlayer(String rtpAudio) {
        try {
            MediaLocator mlr = new MediaLocator(rtpAudio);
            LOGGER.info("create audio player: {}", rtpAudio);
            audioPlayer = Manager.createRealizedPlayer(mlr);

            if (audioPlayer.getVisualComponent() != null) {
                audioPanel.add("Center", audioPlayer.getVisualComponent());
            }
            if (audioPlayer.getControlPanelComponent() != null) {
                audioPanel.add("South", audioPlayer.getControlPanelComponent());
            }
        } catch (Exception e) {
            LOGGER.error("Failed create audio player: {}", rtpAudio);
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
    }

    private void buildTitle() {
        if (contact != null && contact.getConnection() != null) {
            setTitle("You calling to :'" + contact.getName() + "'. Connection information [ip: " + contact.getConnection().getIpAddress() +
                "; vp:" + contact.getConnection().getVideoPort() +
                ", ap:" + contact.getConnection().getAudioPort() + "]");
        }
    }

}
