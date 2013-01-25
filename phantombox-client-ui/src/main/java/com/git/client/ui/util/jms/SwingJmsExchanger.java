package com.git.client.ui.util.jms;

import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.impl.domain.AbstractJmsExchanger;
import com.git.client.api.exception.BroadcastException;
import com.git.client.api.webcam.broadcast.IBroadcaster;
import com.git.client.ui.frame.VideoFrame;
import com.git.client.webcam.broadcast.Broadcaster;
import com.git.domain.api.IConnection;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SwingJmsExchanger.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 4:48 PM
 */
public class SwingJmsExchanger extends AbstractJmsExchanger implements IJmsExchanger {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwingJmsExchanger.class);

    private final IBroadcaster broadcaster = Broadcaster.getInstance();

    @Override
    public void broadcast(final IConnection connection) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    broadcaster.start(connection);
                    LOGGER.info("-------- START VIDEO BROADCAST --------");
                    // audioSender.start(TransmissionType.AUDIO, DSC, connection.getIpAddress(), connection.getAudioPort());
                    //  LOGGER.info("-------- START AUDIO BROADCAST --------");
                } catch (BroadcastException e) {
                    LOGGER.error("-------- FAILED BROADCAST --------");
                    LOGGER.error(ExceptionUtils.getMessage(e));
                }
            }
        });
        thread.start();
    }

    @Override
    public void listen(IConnection connection) {
        StringBuilder video = new StringBuilder("rtp://");
        video.append(connection.getIpAddress())
            .append(":")
            .append(String.valueOf(connection.getVideoPort()))
            .append("/video");
        LOGGER.info("-------- LISTEN {} --------", connection.toString());
        VideoFrame videoFrame = new VideoFrame(video.toString());
        videoFrame.setVisible(true);

        //  TODO crate Call frame and add to CallFrameManager
    }

    @Override
    public void onCallReject(IConnection connection) {
        try {
            // try to stop broadcast
            broadcaster.stop();
        } catch (BroadcastException e) {
            LOGGER.error("-------- FAILED TO STOP BROADCAST --------");
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
    }

}
