package com.git.client.webcam.broadcast;

import static com.git.client.api.webcam.transmitter.TransmissionType.VIDEO;
import com.git.client.api.exception.BroadcastException;
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
import com.git.client.webcam.datasource.DataSourceFactory;
import com.git.client.webcam.device.DeviceManager;
import com.git.client.webcam.locator.MediaLocatorFactory;
import com.git.client.webcam.processor.ProcessorFactory;
import com.git.client.webcam.transmitter.TransmitterFactoryDataSink;
import com.git.domain.api.IConnection;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.media.CaptureDeviceInfo;
import javax.media.Processor;
import javax.media.protocol.DataSource;

/**
 * {@link IBroadcaster} implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 7:15 PM
 */
public class Broadcaster implements IBroadcaster {

    private ITransmitterFactory transmitterFactory;

    private IProcessorFactory processorFactory;

    private IDeviceManager deviceManager;

    private IDataSourceFactory dataSourceFactory;

    private IMediaLocatorFactory mediaLocatorFactory;

    private Table<TransmissionType, IConnection, Processor> processorPool = HashBasedTable.create();

    private static final Logger LOGGER = LoggerFactory.getLogger(Broadcaster.class);

    private static volatile Broadcaster instance;

    /**
     * Default constructor.
     */
    private Broadcaster() {
        this.transmitterFactory = new TransmitterFactoryDataSink();
        this.processorFactory = new ProcessorFactory();
        this.deviceManager = new DeviceManager();
        this.dataSourceFactory = new DataSourceFactory();
        this.mediaLocatorFactory = new MediaLocatorFactory();

        this.deviceManager.initDevices();
    }

    public static Broadcaster getInstance() {
        Broadcaster localInstance = instance;
        if (localInstance == null) {
            synchronized (Broadcaster.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Broadcaster();
                }
            }
        }
        return localInstance;
    }

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
    @Deprecated
    public void start(TransmissionType type, CaptureDeviceInfo device, IConnection connection)
        throws BroadcastException {
        Processor processor;
        if (!processorPool.contains(type, connection)) {
            processor = createProcessor(device);
            processorPool.put(type, connection, processor);
        } else {
            processor = processorPool.get(type, connection);
        }
        start(processor, type, connection);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void start(TransmissionType type, String device, IConnection connection)
        throws BroadcastException {
        try {
            CaptureDeviceInfo deviceInfo = deviceManager.getCaptureDeviceInfo(device);
            start(type, deviceInfo, connection);
        } catch (DeviceNotFoundException e) {
            throw new BroadcastException("device not found", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(TransmissionType type, IConnection connection) throws BroadcastException {
        if (!processorPool.isEmpty()) {
            Processor processor = processorPool.get(type, connection);
            if (processor != null) {
                disposeTransmitter(type, connection);
                processor.stop();
                LOGGER.info("Transmission " + type.getValue() + "for connection: " + connection + " is stopped.");
            } else {
                throw new BroadcastException("Transmission " + type.getValue() + "for connection: " + connection + " not running.");
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public void stop() {
        if (!processorPool.isEmpty()) {
            for (Processor processor : processorPool.values()) {
                processor.stop();
            }
        }
    }

    private Processor createProcessor(CaptureDeviceInfo device) throws BroadcastException {
        Processor processor;
        try {
            DataSource dataSource = dataSourceFactory.createDataSource(
                mediaLocatorFactory.createMediaLocator(device));
            processor = processorFactory.createProcessor(dataSource);
        } catch (MediaLocatorCreationException e) {
            throw new BroadcastException("failed create MediaLocator", e);
        } catch (DataSourceCreationException e) {
            throw new BroadcastException("failed create dataSource", e);
        } catch (ProcessorCreationException e) {
            throw new BroadcastException("failed create processor", e);
        }
        return processor;
    }

    private void start(Processor processor, TransmissionType type, IConnection connection)
        throws BroadcastException {
        try {
            int port;
            LOGGER.info("Broadcaster::start(); Create transmitter. type={}, connection = {},video_port = {} ",
                new String[]{type.getValue(), connection.toString()});
            // TODO move it to transmitterFactory
            if (VIDEO.equals(type)) {
                port = connection.getVideoPort();
            } else {
                port = connection.getAudioPort();
            }
            transmitterFactory.createTransmitter(processor.getDataOutput(), connection.getIpAddress(), port, type);
            processor.start();
            LOGGER.info("Broadcaster::start(); Processor is started.");
            LOGGER.info("Broadcaster::start(); Started transferring data.");
        } catch (TransmitterException e) {
            throw new BroadcastException("failed transferring data ");
        }

    }

    // TODO move it to transmitterFactory
    private void disposeTransmitter(TransmissionType type, IConnection connection) throws BroadcastException {
        int port;

        if (VIDEO.equals(type)) {
            port = connection.getVideoPort();
        } else {
            port = connection.getAudioPort();
        }
        try {
            transmitterFactory.disposeTransmitter(connection.getIpAddress(), port, type);
        } catch (TransmitterException e) {
            throw new BroadcastException("Transmission " + type.getValue() + "for connection: " + connection + " not running.", e);
        }

    }
}
