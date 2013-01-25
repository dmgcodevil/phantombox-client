package com.git.client.webcam.transmitter;


import com.git.client.api.domain.DeviceType;
import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.exception.TransmitterException;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.webcam.util.UrlUtil;
import com.git.domain.api.IConnection;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaException;
import javax.media.MediaLocator;
import javax.media.protocol.DataSource;

/**
 * {@link ITransmitterFactory} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/7/12
 * Time: 10:31 AM
 */
public class TransmitterFactoryDataSink implements ITransmitterFactory {


    private Map<String, DataSink> dataSinkMap = new HashMap();

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTransmitter(DataSource dataOutput, IConnection connection,
                                  ICaptureDevice device)
        throws TransmitterException {
        DataSink rtptransmitter;

        String rtpURL = buildUrl(connection, device);
        MediaLocator outputLocator = new MediaLocator(rtpURL);

        // Create a data sink, open it and start transmission. It will wait
        // for the processor to start sending data. So we need to start the
        // output data source of the processor. We also need to start the
        // processor itself, which is done after this method returns.
        try {
            rtptransmitter = Manager.createDataSink(dataOutput, outputLocator);
            rtptransmitter.open();
            rtptransmitter.start();
            dataOutput.start();
            // TODO if DataSink already in pull throw TransmitterException ?
            dataSinkMap.put(rtpURL, rtptransmitter);
        } catch (MediaException me) {
            throw new TransmitterException("Couldn't create RTP data sink", me);
        } catch (IOException ioe) {
            throw new TransmitterException("Couldn't create RTP data sink", ioe);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public void disposeTransmitter(IConnection connection, ICaptureDevice device)
        throws TransmitterException {
        if (MapUtils.isNotEmpty(dataSinkMap)) {
            DataSink rtptransmitter = dataSinkMap.remove(buildUrl(connection, device));
            if (rtptransmitter != null) {
                try {
                    rtptransmitter.stop();
                    rtptransmitter.close();
                } catch (IOException ex) {
                    throw new TransmitterException("Error to dispose.", ex);
                }
            } else {
                throw new TransmitterException("Connection not exist.");
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disposeTransmitter() throws TransmitterException {
        if (MapUtils.isNotEmpty(dataSinkMap)) {
            for (Map.Entry<String, DataSink> sinkEntry : dataSinkMap.entrySet()) {
                try {
                    sinkEntry.getValue().stop();
                } catch (IOException e) {
                    throw new TransmitterException("Failed stop transmitter for: " +
                        sinkEntry.getKey());
                }
            }
        }
    }

    private String buildUrl(IConnection connection, ICaptureDevice device) {
        int port;
        if (DeviceType.AUDIO.equals(device.getDeviceType())) {
            port = connection.getAudioPort();
        } else {
            port = connection.getVideoPort();
        }
        return UrlUtil.buildUrl(UrlUtil.RTP, connection.getIpAddress(), port,
            device.getDeviceType());
    }

}
