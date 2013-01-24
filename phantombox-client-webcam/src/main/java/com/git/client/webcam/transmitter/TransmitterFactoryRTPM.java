package com.git.client.webcam.transmitter;


import com.git.client.api.domain.DeviceType;
import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.exception.TransmitterException;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.api.webcam.transmitter.TransmissionType;
import com.git.client.webcam.util.UrlUtil;
import com.git.domain.api.IConnection;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import javax.media.format.UnsupportedFormatException;
import javax.media.protocol.DataSource;
import javax.media.rtp.InvalidSessionAddressException;
import javax.media.rtp.RTPManager;
import javax.media.rtp.SendStream;
import javax.media.rtp.SessionAddress;

/**
 * {@link ITransmitterFactory} interface implementation.
 * <p/>
 * Creates transmitter. Use the RTPManager API to
 * create sessions for each media track of the processor.
 * <p/>
 * Date: 06.11.12
 * Time: 15:50
 *
 * @author rpleshkov
 */
public class TransmitterFactoryRTPM implements ITransmitterFactory {

    private Map<String, ConnectionInfo> managers = new HashMap();

    private static final Logger LOGGER = LoggerFactory.getLogger(TransmitterFactoryRTPM.class);

    public Map<String, ConnectionInfo> getManagers() {
        return managers;
    }

    public void setManagers(Map<String, ConnectionInfo> managers) {
        this.managers = managers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTransmitter(DataSource dataOutput, IConnection connection, ICaptureDevice device)
        throws TransmitterException {

        // create the RTP Manager
        RTPManager rtpManager = RTPManager.newInstance();

        // create the local endpoint for the local interface on
        // any local port
        SessionAddress localAddress = new SessionAddress();

        // initialize the RTPManager
        try {
            rtpManager.initialize(localAddress);


            // add the ReceiveStreamListener if you need to receive data
            // and do other application specific stuff
            // ...

            // specify the remote endpoint of this unicast session
            // the address string and port numbers in the following lines
            // need to be replaced with your values.
            InetAddress inetAddress = InetAddress.getByName(connection.getIpAddress());

            SessionAddress remoteAddress = new SessionAddress(inetAddress, getPort(connection, device));

            // open the connection
            rtpManager.addTarget(remoteAddress);

            SendStream sendStream = rtpManager.createSendStream(dataOutput, 1);
            sendStream.start();

            managers.put(buildUrl(connection, device),
                new ConnectionInfo(rtpManager, remoteAddress));

        } catch (InvalidSessionAddressException ex) {
            throw new TransmitterException("invalid session address", ex);
        } catch (IOException ex) {
            throw new TransmitterException("IOException", ex);
        } catch (UnsupportedFormatException ex) {
            throw new TransmitterException("Unsupported format", ex);
        }


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disposeTransmitter(IConnection connection, ICaptureDevice device)
        throws TransmitterException {
        if (MapUtils.isNotEmpty(managers)) {
            ConnectionInfo connectionInfo = managers.remove(buildUrl(connection, device));
            if (connectionInfo != null) {
                // close the connection if no longer needed.
                try {
                    connectionInfo.getRtpManager().removeTarget(connectionInfo.getRemoteAddress(),
                        "Client disconnected.");
                    // call dispose at the end of the life-cycle of this RTPManager so
                    // it is prepared to be garbage-collected.
                    connectionInfo.getRtpManager().dispose();
                } catch (InvalidSessionAddressException ex) {
                    throw new TransmitterException("Error to dispose.", ex);
                }
            } else {
                throw new TransmitterException("Connection not exist.");
            }
        }
    }

    @Override
    public void disposeTransmitter() throws TransmitterException {
        // TODO implement it
    }

    private String buildUrl(IConnection connection, ICaptureDevice device) {
        return UrlUtil.buildUrl(UrlUtil.RTP, connection.getIpAddress(), getPort(connection, device), device.getDeviceType());
    }

    private int getPort(IConnection connection, ICaptureDevice device) {
        int port;
        if (DeviceType.AUDIO.equals(device.getDeviceType())) {
            port = connection.getAudioPort();
        } else {
            port = connection.getVideoPort();
        }
        return port;
    }

    private class ConnectionInfo {

        private RTPManager rtpManager;

        private SessionAddress remoteAddress;

        private ConnectionInfo(RTPManager rtpManager, SessionAddress remoteAddress) {
            this.rtpManager = rtpManager;
            this.remoteAddress = remoteAddress;
        }

        public RTPManager getRtpManager() {
            return rtpManager;
        }

        public void setRtpManager(RTPManager rtpManager) {
            this.rtpManager = rtpManager;
        }

        public SessionAddress getRemoteAddress() {
            return remoteAddress;
        }

        public void setRemoteAddress(SessionAddress remoteAddress) {
            this.remoteAddress = remoteAddress;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ConnectionInfo)) {
                return false;
            }

            ConnectionInfo that = (ConnectionInfo) o;
            return new EqualsBuilder()
                .append(rtpManager, that.rtpManager)
                .append(remoteAddress, that.remoteAddress)
                .isEquals();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                .append(rtpManager)
                .append(remoteAddress)
                .toHashCode();
        }
    }

}
