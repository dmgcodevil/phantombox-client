package com.git.client.api.webcam.broadcast;

import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.exception.BroadcastException;
import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.domain.api.IConnection;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 7:15 PM
 */
public interface IBroadcaster {

    /**
     * Gets transmitter Factory.
     *
     * @return {@link com.git.client.api.webcam.transmitter.ITransmitterFactory}
     */
    ITransmitterFactory getTransmitterFactory();

    /**
     * Sets transmitter Factory.
     *
     * @param transmitterFactory {@link ITransmitterFactory}
     */
    void setTransmitterFactory(ITransmitterFactory transmitterFactory);

    /**
     * Gets processor factory.
     *
     * @return {@link com.git.client.api.webcam.processor.IProcessorFactory}
     */
    IProcessorFactory getProcessorFactory();

    /**
     * Sets processor factory.
     *
     * @param processorFactory {@link IProcessorFactory}
     */
    void setProcessorFactory(IProcessorFactory processorFactory);

    /**
     * Gets device manager.
     *
     * @return {@link com.git.client.api.webcam.device.IDeviceManager}
     */
    IDeviceManager getDeviceManager();

    /**
     * Sets device manager.
     *
     * @param deviceManager {@link IDeviceManager}
     */
    void setDeviceManager(IDeviceManager deviceManager);

    /**
     * Gets data source factory.
     *
     * @return {@link com.git.client.api.webcam.datasource.IDataSourceFactory}
     */
    IDataSourceFactory getDataSourceFactory();

    /**
     * Sets data source factory.
     *
     * @param dataSourceFactory {@link IDataSourceFactory}
     */
    void setDataSourceFactory(IDataSourceFactory dataSourceFactory);

    /**
     * Gets media locator factory.
     *
     * @return {@link com.git.client.api.webcam.locator.IMediaLocatorFactory}
     */
    IMediaLocatorFactory getMediaLocatorFactory();

    /**
     * Sets media locator factory.
     *
     * @param mediaLocatorFactory {@link IMediaLocatorFactory}
     */
    void setMediaLocatorFactory(IMediaLocatorFactory mediaLocatorFactory);

    /**
     * Start broadcasting.
     *
     * @param connection {@link IConnection}
     * @throws BroadcastException {@link BroadcastException}
     */
    void start(IConnection connection) throws BroadcastException;

    /**
     * Start transmission.
     *
     * @param device     {@link ICaptureDevice}
     * @param connection {@link IConnection}
     * @throws BroadcastException {@link BroadcastException}
     */
    void start(ICaptureDevice device, IConnection connection) throws BroadcastException;


    /**
     * Forced stop broadcast for current device and connection.
     * Using this method is dangerous. Better use stop() method.
     *
     * @param device     {@link ICaptureDevice}
     * @param connection {@link IConnection}
     * @throws BroadcastException {@link BroadcastException}
     */
    void stop(ICaptureDevice device, IConnection connection) throws BroadcastException;

    /**
     * Stop the broadcast. If the current broadcast has no one subscriber than stop broadcast.
     *
     * @throws BroadcastException {@link BroadcastException}
     */
    void stop() throws BroadcastException;
}
