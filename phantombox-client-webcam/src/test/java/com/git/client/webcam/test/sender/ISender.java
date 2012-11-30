package com.git.client.webcam.test.sender;


import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.api.webcam.transmitter.TransmissionType;

import java.util.Map;
import javax.media.CaptureDeviceInfo;
import javax.media.Processor;

/**
 * Sender.
 * <p/>
 * User: dmgcodevil
 * Date: 11/7/12
 * Time: 11:41 AM
 */
public interface ISender {

    /**
     * Gets transmitter Factory.
     *
     * @return {@link ITransmitterFactory}
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
     * @return {@link IProcessorFactory}
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
     * @return {@link IDeviceManager}
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
     * @return {@link IDataSourceFactory}
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
     * @return {@link IMediaLocatorFactory}
     */
    IMediaLocatorFactory getMediaLocatorFactory();

    /**
     * Sets media locator factory.
     *
     * @param mediaLocatorFactory {@link IMediaLocatorFactory}
     */
    void setMediaLocatorFactory(IMediaLocatorFactory mediaLocatorFactory);

    /**
     * Gets processor pool.
     *
     * @return processor pool
     */
    Map<TransmissionType, Processor> getProcessorPool();

    /**
     * Set processor pool.
     *
     * @param processorPool processor pool
     */
    void setProcessorPool(Map<TransmissionType, Processor> processorPool);


    /**
     * Start transmission.
     *
     * @param type    {@link TransmissionType}
     * @param device  {@link CaptureDeviceInfo}
     * @param address address
     * @param port    port
     */
    void start(TransmissionType type, CaptureDeviceInfo device, String address, int port);

    /**
     * Start transmission.
     *
     * @param type    {@link TransmissionType}
     * @param device  device
     * @param address address
     * @param port    port
     */
    void start(TransmissionType type, String device, String address, int port);

    /**
     * Stop current transmission.
     *
     * @param type {@link TransmissionType}
     */
    void stop(TransmissionType type);

    /**
     * Stop all transmissions.
     */
    void stop();
}
