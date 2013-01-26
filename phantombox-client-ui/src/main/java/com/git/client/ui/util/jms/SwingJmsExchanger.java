package com.git.client.ui.util.jms;

import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.impl.domain.AbstractJmsExchanger;
import com.git.client.api.exception.BroadcastException;
import com.git.client.api.ui.IMediator;
import com.git.client.api.ui.ISwingJmsExchanger;
import com.git.client.api.webcam.broadcast.IBroadcaster;
import com.git.client.ui.frame.CallFrame;
import com.git.client.ui.frame.VideoFrame;
import com.git.client.ui.util.CallFrameManager;
import com.git.client.webcam.broadcast.Broadcaster;
import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SwingJmsExchanger.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 4:48 PM
 */
public class SwingJmsExchanger extends AbstractJmsExchanger implements ISwingJmsExchanger {


    private IMediator mediator;

    private static final String VIDEO = "video";
    private static final String AUDIO = "audio";

    private static final Logger LOGGER = LoggerFactory.getLogger(SwingJmsExchanger.class);

    private final IBroadcaster broadcaster = Broadcaster.getInstance();


    /**
     * {@inheritDoc}
     */
    @Override
    public void broadcast(final IConnection connection) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    broadcaster.start(connection);
                } catch (BroadcastException e) {
                    LOGGER.error("-------- FAILED BROADCAST --------");
                    LOGGER.error(ExceptionUtils.getMessage(e));
                }
            }
        });
        thread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void listen(IContact contact) {
        IConnection connection = contact.getConnection();
        LOGGER.info("-------- LISTEN {} --------", connection.toString());
        String rtpVideo = buildRTP(connection.getIpAddress(), connection.getVideoPort(), VIDEO);
        String rtpAudio = buildRTP(connection.getIpAddress(), connection.getAudioPort(), AUDIO);

        mediator.createCallFrame(rtpVideo, rtpAudio, contact);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCallStop(IContact contact) {
        LOGGER.info("onCallStop()::contact={}", contact.getConnection(), contact);
        mediator.disposeCallFrame(contact);
        onCallReject();
    }


    private void onCallReject() {
        try {
            // try to stop broadcast
            broadcaster.stop();
        } catch (BroadcastException e) {
            LOGGER.error("-------- FAILED TO STOP BROADCAST --------");
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
    }

    private String buildRTP(String ipAddress, int port, String type) {
        StringBuilder rtp = new StringBuilder("rtp://");
        rtp.append(ipAddress)
            .append(":")
            .append(String.valueOf(port))
            .append("/")
            .append(type);
        return rtp.toString();
    }

    @Override
    public IMediator getMediator() {
        return mediator;
    }

    @Autowired
    @Override
    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }
}
