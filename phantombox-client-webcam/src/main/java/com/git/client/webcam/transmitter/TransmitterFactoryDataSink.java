package com.git.client.webcam.transmitter;


import com.git.client.api.exception.TransmitterException;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.api.webcam.transmitter.TransmissionType;
import com.git.client.webcam.util.UrlUtil;
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
    public void createTransmitter(DataSource dataOutput, String ipAddress, int port,
                                  TransmissionType type) throws TransmitterException {
        DataSink rtptransmitter = null;
        String rtpURL = UrlUtil.buildUrl(UrlUtil.RTP, ipAddress, port, type);
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
    public void disposeTransmitter(String ipAddress, int port, TransmissionType type)
        throws TransmitterException {
        if (MapUtils.isNotEmpty(dataSinkMap)) {
            DataSink rtptransmitter = dataSinkMap.remove(UrlUtil.buildUrl(UrlUtil.RTP, ipAddress,
                port, type));
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

}
