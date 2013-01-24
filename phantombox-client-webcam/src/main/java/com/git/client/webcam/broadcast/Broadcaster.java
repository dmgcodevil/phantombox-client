package com.git.client.webcam.broadcast;

import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.exception.BroadcastException;
import com.git.client.api.exception.DataSourceCreationException;
import com.git.client.api.exception.MediaLocatorCreationException;
import com.git.client.api.exception.ProcessorCreationException;
import com.git.client.api.exception.TransmitterException;
import com.git.client.api.webcam.broadcast.IBroadcaster;
import com.git.client.api.webcam.datasource.IDataSourceFactory;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;
import com.git.client.api.webcam.processor.IProcessorFactory;
import com.git.client.api.webcam.transmitter.ITransmitterFactory;
import com.git.client.webcam.datasource.DataSourceFactory;
import com.git.client.webcam.device.DeviceManager;
import com.git.client.webcam.locator.MediaLocatorFactory;
import com.git.client.webcam.processor.ProcessorFactory;
import com.git.client.webcam.transmitter.TransmitterFactoryDataSink;
import com.git.domain.api.IConnection;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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

    private HashMap<ICaptureDevice, Processor> processorPool = new HashMap();

    private ThreadLocal<Long> countSubscribers = new ThreadLocal();

    private static final Logger LOGGER = LoggerFactory.getLogger(Broadcaster.class);

    private static volatile Broadcaster instance;

    private static final Long EMPTY_SUBSCRIBERS = 0L;

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
        countSubscribers.set(EMPTY_SUBSCRIBERS);
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
    public void start(IConnection connection) throws BroadcastException {
        ICaptureDevice videoDevice = deviceManager.getVideoDevice();
        LOGGER.info("device {} successfully loaded", videoDevice);
        ICaptureDevice audioDevice = deviceManager.getAudioDevice();
        LOGGER.info("device {} successfully loaded", audioDevice);

        LOGGER.info("Start video broadcast", audioDevice);
        start(videoDevice, connection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public void start(ICaptureDevice device, IConnection connection)
        throws BroadcastException {
        Processor processor;
        if (!processorPool.containsKey(device)) {
            processor = createProcessor(device);
            processorPool.put(device, processor);
        } else {
            processor = processorPool.get(device);
        }
        start(processor, device, connection);
        addSubscriber();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public void stop(ICaptureDevice device, IConnection connection) throws BroadcastException {
        if (!processorPool.isEmpty()) {
            Processor processor = processorPool.get(device);
            if (processor != null) {
                disposeTransmitter(device, connection);
                processor.stop();
                countSubscribers.remove();
                LOGGER.info("Broadcast for device" + device + ", connection: " + connection + " is stopped.");
            } else {
                throw new BroadcastException("Failed to stop Broadcast for device" + device + ", connection: " + connection);
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() throws BroadcastException {
        if (EMPTY_SUBSCRIBERS.equals(countSubscribers.get()) && MapUtils.isNotEmpty(processorPool)) {
            for (Processor processor : processorPool.values()) {
                processor.stop();
            }
        } else {
            removeSubscriber();
        }
    }

    private Processor createProcessor(ICaptureDevice captureDevice) throws BroadcastException {
        Processor processor;
        try {
            DataSource dataSource = dataSourceFactory.createDataSource(
                mediaLocatorFactory.createMediaLocator(captureDevice.getCaptureDeviceInfo()));
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

    private void start(Processor processor, ICaptureDevice device, IConnection connection)
        throws BroadcastException {
        try {
            int port;
            LOGGER.info("Broadcaster::start(); Create transmitter. device={}, connection = {}",
                new String[]{device.getCaptureDeviceInfo().getName(), connection.toString()});

            transmitterFactory.createTransmitter(processor.getDataOutput(), connection, device);
            processor.start();
            LOGGER.info("Broadcaster::start(); Processor is started.");
            LOGGER.info("Broadcaster::start(); Started transferring data.");
        } catch (TransmitterException e) {
            throw new BroadcastException("failed transferring data ");
        }

    }

    private void disposeTransmitter(ICaptureDevice device, IConnection connection) throws BroadcastException {
        try {
            transmitterFactory.disposeTransmitter(connection, device);
        } catch (TransmitterException e) {
            throw new BroadcastException("Transmission device" + device + ", connection: " + connection + " not running.", e);
        }

    }

    private synchronized void addSubscriber() {
        Long currentCount = countSubscribers.get();
        currentCount++;
        countSubscribers.set(currentCount);
    }

    private synchronized void removeSubscriber() {
        Long currentCount = countSubscribers.get();
        if (currentCount > EMPTY_SUBSCRIBERS)
            currentCount--;
        countSubscribers.set(currentCount);
    }
}
