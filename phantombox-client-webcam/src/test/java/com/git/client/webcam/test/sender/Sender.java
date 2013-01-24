package com.git.client.webcam.test.sender;


import com.git.client.api.exception.DataSourceCreationException;
import com.git.client.api.exception.DeviceNotFoundException;
import com.git.client.api.exception.MediaLocatorCreationException;
import com.git.client.api.exception.ProcessorCreationException;
import com.git.client.api.exception.TransmitterException;
import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.api.webcam.transmitter.TransmissionType;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import javax.media.CaptureDeviceInfo;
import javax.media.Processor;
import javax.media.protocol.DataSource;

/**
 * {@link ISender} implementation.
 * <p/>
 * Date: 06.11.12
 * Time: 16:30
 *
 * @author rpleshkov
 */
public class Sender implements ISender {

    private ITransmitterFactory transmitterFactory;

    private IProcessorFactory processorFactory;

    private IDeviceManager deviceManager;

    private IDataSourceFactory dataSourceFactory;

    private IMediaLocatorFactory mediaLocatorFactory;

    private Map<TransmissionType, Processor> processorPool = new HashMap();

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public ITransmitterFactory getTransmitterFactory() {
        return transmitterFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransmitterFactory(ITransmitterFactory transmitterFactory) {
        this.transmitterFactory = transmitterFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IProcessorFactory getProcessorFactory() {
        return processorFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProcessorFactory(IProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeviceManager getDeviceManager() {
        return deviceManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeviceManager(IDeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDataSourceFactory(IDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IMediaLocatorFactory getMediaLocatorFactory() {
        return mediaLocatorFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediaLocatorFactory(IMediaLocatorFactory mediaLocatorFactory) {
        this.mediaLocatorFactory = mediaLocatorFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<TransmissionType, Processor> getProcessorPool() {
        return processorPool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProcessorPool(Map<TransmissionType, Processor> processorPool) {
        this.processorPool = processorPool;
    }

    @Override
    public void start(TransmissionType type, CaptureDeviceInfo device, String address, int port) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start(TransmissionType type, String device, String address, int port) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stop(TransmissionType type) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stop() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
