package com.git.client.webcam.util;

import com.git.client.api.webcam.broadcast.IBroadcaster;
import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.webcam.broadcast.Broadcaster;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 7:17 PM
 */
// TODO remove it if unnecessary
public class BroadcasterBuilder {
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
     * @return {@link IBroadcaster}
     */
    public IBroadcaster getSender() {
        IBroadcaster broadcaster = new Broadcaster();
        broadcaster.setTransmitterFactory(transmitterFactoryBuilder);
        broadcaster.setProcessorFactory(processorFactoryBuilder);
        broadcaster.setDeviceManager(deviceManagerBuilder);
        broadcaster.setDataSourceFactory(dataSourceFactoryBuilder);
        broadcaster.setMediaLocatorFactory(mediaLocatorFactoryBuilder);
        return broadcaster;
    }
}
