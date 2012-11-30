package com.git.client.webcam.test.sender;

import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;

/**
 * Enter class description.
 * <p/>
 * Date: 08.11.12
 * Time: 16:27
 *
 * @author rpleshkov
 */
public class SenderBuilder {

    private ITransmitterFactory transmitterFactoryBuilder;

    private IProcessorFactory processorFactoryBuilder;

    private IDeviceManager deviceManagerBuilder;

    private IDataSourceFactory dataSourceFactoryBuilder;

    private IMediaLocatorFactory mediaLocatorFactoryBuilder;

    /**
     * Builds transmitter factory.
     *
     * @param transmitterFactory {@link ITransmitterFactory}
     */
    public void buildTransmitterFactory(ITransmitterFactory transmitterFactory) {
        this.transmitterFactoryBuilder = transmitterFactory;
    }

    /**
     * Builds processor factory.
     *
     * @param processorFactory {@link IProcessorFactory}
     */
    public void buildProcessorFactory(IProcessorFactory processorFactory) {
        this.processorFactoryBuilder = processorFactory;
    }

    /**
     * Builds device manager.
     *
     * @param deviceManager {@link IDeviceManager}
     */
    public void buildDeviceManager(IDeviceManager deviceManager) {
        this.deviceManagerBuilder = deviceManager;
    }

    /**
     * Builds data source factory.
     *
     * @param dataSourceFactory {@link IDataSourceFactory}
     */
    public void buildDataSourceFactory(IDataSourceFactory dataSourceFactory) {
        this.dataSourceFactoryBuilder = dataSourceFactory;
    }

    /**
     * Builds media locator factory.
     *
     * @param mediaLocatorFactory {@link IMediaLocatorFactory}
     */
    public void buildMediaLocatorFactory(IMediaLocatorFactory mediaLocatorFactory) {
        this.mediaLocatorFactoryBuilder = mediaLocatorFactory;
    }

    /**
     * Gets sender.
     *
     * @return {@link ISender}
     */
    public ISender getSender() {
        ISender sender = new Sender();
        sender.setTransmitterFactory(transmitterFactoryBuilder);
        sender.setProcessorFactory(processorFactoryBuilder);
        sender.setDeviceManager(deviceManagerBuilder);
        sender.setDataSourceFactory(dataSourceFactoryBuilder);
        sender.setMediaLocatorFactory(mediaLocatorFactoryBuilder);
        return sender;
    }
}
