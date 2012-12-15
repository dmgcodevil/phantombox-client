package com.git.client.ui.util.jms;

import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.impl.domain.AbstractJmsExchanger;
import com.git.client.api.webcam.broadcast.IBroadcaster;
import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.api.webcam.transmitter.TransmissionType;
import com.git.client.ui.Mediator;
import com.git.client.ui.frame.VideoFrame;
import com.git.client.webcam.datasource.DataSourceFactory;
import com.git.client.webcam.device.DeviceManager;
import com.git.client.webcam.locator.MediaLocatorFactory;
import com.git.client.webcam.processor.ProcessorFactory;
import com.git.client.webcam.transmitter.TransmitterFactoryDataSink;
import com.git.client.webcam.util.BroadcasterBuilder;
import com.git.domain.api.IConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 4:48 PM
 */
public class SwingJmsExchanger extends AbstractJmsExchanger implements IJmsExchanger {

    public static final String VFM_WDM = "vfw:Microsoft WDM Image Capture (Win32):0";
    public static final String DSC = "DirectSoundCapture";
    private static final Logger LOGGER = LoggerFactory.getLogger(SwingJmsExchanger.class);


    @Override
    public void broadcast(final IConnection connection) {
        BroadcasterBuilder broadcasterBuilder = new BroadcasterBuilder();
        ITransmitterFactory transmitterFactory = new TransmitterFactoryDataSink();
        IProcessorFactory processorFactory = new ProcessorFactory();
        IDeviceManager deviceManager = new DeviceManager();
        IDataSourceFactory dataSourceFactory = new DataSourceFactory();
        IMediaLocatorFactory mediaLocatorFactory = new MediaLocatorFactory();

        deviceManager.initDevices();

        broadcasterBuilder.buildDataSourceFactory(dataSourceFactory);
        broadcasterBuilder.buildDeviceManager(deviceManager);
        broadcasterBuilder.buildMediaLocatorFactory(mediaLocatorFactory);
        broadcasterBuilder.buildProcessorFactory(processorFactory);
        broadcasterBuilder.buildTransmitterFactory(transmitterFactory);
        final IBroadcaster videoBroadcaster = broadcasterBuilder.getSender();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                videoBroadcaster.start(TransmissionType.VIDEO, VFM_WDM, connection.getIpAddress(), connection.getVideoPort());
            }
        });
        thread.start();

        LOGGER.info("-------- START VIDEO BROADCAST --------");
        // IBroadcaster audioSender = broadcasterBuilder.getSender();
        // audioSender.start(TransmissionType.AUDIO, DSC, connection.getIpAddress(), connection.getAudioPort());
        //  LOGGER.info("-------- START AUDIO BROADCAST --------");
    }

    @Override
    public void listen(IConnection connection) {
        StringBuilder video = new StringBuilder("rtp://");
        video.append(connection.getIpAddress())
            .append(":")
            .append(String.valueOf(connection.getVideoPort()))
            .append("/video");
        VideoFrame videoFrame = new VideoFrame(video.toString());
        videoFrame.setVisible(true);
    }

}
