package com.git.client.webcam.broadcast;

import com.git.client.api.exception.DataSourceCreationException;
import com.git.client.api.exception.DeviceNotFoundException;
import com.git.client.api.exception.MediaLocatorCreationException;
import com.git.client.api.exception.ProcessorCreationException;
import com.git.client.api.exception.TransmitterException;
import com.git.client.api.webcam.broadcast.IBroadcaster;
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
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 7:15 PM
 */
public class Broadcaster implements IBroadcaster{
    private ITransmitterFactory transmitterFactory;

    private IProcessorFactory processorFactory;

    private IDeviceManager deviceManager;

    private IDataSourceFactory dataSourceFactory;

    private IMediaLocatorFactory mediaLocatorFactory;

    private Map<TransmissionType, Processor> processorPool = new HashMap();

    private static final Logger LOGGER = LoggerFactory.getLogger(Broadcaster.class);

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


    /**
     * {@inheritDoc}
     */
    @Override
    public void start(TransmissionType type, CaptureDeviceInfo device, String address, int port) {
        try {
            DataSource dataSource = dataSourceFactory.createDataSource(
                mediaLocatorFactory.createMediaLocator(device));
            Processor processor = processorFactory.createProcessor(dataSource);
            transmitterFactory.createTransmitter(processor.getDataOutput(), address, port, type);
            processor.start();
            LOGGER.info("Processor is started.");
            LOGGER.info("Started transferring data.");
            processorPool.put(type, processor);
        } catch (DataSourceCreationException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
        } catch (ProcessorCreationException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
        } catch (TransmitterException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
        } catch (MediaLocatorCreationException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void start(TransmissionType type, String device, String address, int port) {
        try {
            CaptureDeviceInfo deviceInfo = deviceManager.getCaptureDeviceInfo(device);
            start(type, deviceInfo, address, port);
        } catch (DeviceNotFoundException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(TransmissionType type) {
        if (MapUtils.isNotEmpty(processorPool)) {
            Processor processor = processorPool.get(type);
            if (processor != null) {
                processor.stop();
                LOGGER.info("Transmission " + type.getValue() + " is stopped.");
            } else {
                LOGGER.error("Transmission " + type.getValue() + " not running.");
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (MapUtils.isNotEmpty(processorPool)) {
            for (TransmissionType type : processorPool.keySet()) {
                stop(type);
            }
        }
    }
}
