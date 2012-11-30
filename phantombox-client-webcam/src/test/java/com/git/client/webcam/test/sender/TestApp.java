package com.git.client.webcam.test.sender;

import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.api.webcam.transmitter.TransmissionType;
import com.git.client.webcam.datasource.DataSourceFactory;
import com.git.client.webcam.device.DeviceManager;
import com.git.client.webcam.locator.MediaLocatorFactory;
import com.git.client.webcam.processor.ProcessorFactory;
import com.git.client.webcam.transmitter.TransmitterFactoryDataSink;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 11/8/12
 * Time: 2:13 PM
 */
public class TestApp {

    public static final String VFM_WDM = "vfw:Microsoft WDM Image Capture (Win32):0";
    public static final String DSC = "DirectSoundCapture";

    public static void main(String[] args) {
        SenderBuilder senderBuilder = new SenderBuilder();
        ITransmitterFactory transmitterFactory = new TransmitterFactoryDataSink();
        IProcessorFactory processorFactory = new ProcessorFactory();
        IDeviceManager deviceManager = new DeviceManager();
        IDataSourceFactory dataSourceFactory = new DataSourceFactory();
        IMediaLocatorFactory mediaLocatorFactory = new MediaLocatorFactory();

        deviceManager.initDevices();

        senderBuilder.buildDataSourceFactory(dataSourceFactory);
        senderBuilder.buildDeviceManager(deviceManager);
        senderBuilder.buildMediaLocatorFactory(mediaLocatorFactory);
        senderBuilder.buildProcessorFactory(processorFactory);
        senderBuilder.buildTransmitterFactory(transmitterFactory);
        ISender videoSender = senderBuilder.getSender();
        videoSender.start(TransmissionType.VIDEO, VFM_WDM, "224.123.111.101", 22224);
        ISender audioSender = senderBuilder.getSender();
        audioSender.start(TransmissionType.AUDIO, DSC, "224.123.111.101", 22225);
    }
}
