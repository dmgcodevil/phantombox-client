package com.git.client.webcam.device;

import static com.git.domain.api.Constants.FILE_NAME;
import com.git.client.api.domain.DeviceType;
import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.domain.IDeviceStatistic;
import com.git.client.api.exception.DeviceNotFoundException;
import com.git.client.api.exception.PropertiesException;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.device.IPropertiesManager;
import com.git.client.domain.CaptureDevice;
import com.git.client.domain.DeviceStatistic;
import com.git.domain.api.ISetup;
import com.git.domain.impl.Device;
import com.git.domain.impl.Setup;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;

/**
 * {@link IDeviceManager} interface implementation.
 * <p/>
 * Date: 06.11.12
 * Time: 12:21
 *
 * @author rpleshkov
 */
public class DeviceManager implements IDeviceManager {

    private Map<String, CaptureDeviceInfo> devices;

    private ICaptureDevice audioDevice;

    private ICaptureDevice videoDevice;

    private IPropertiesManager propertiesManager = new PropertiesManager();

    private Set<String> videoDevices = new HashSet();

    private Set<String> audioDevices = new HashSet();

    private static final Format ALL_DEVICES = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceManager.class);

    private static final String DEFAULT_AUDIO_DEVICE = "default.audio.device";

    private static final String DEFAULT_VIDEO_DEVICE = "default.video.device";

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, CaptureDeviceInfo> getDevices() {
        return devices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDevices(Map<String, CaptureDeviceInfo> devices) {
        this.devices = devices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICaptureDevice getAudioDevice() {
        return audioDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICaptureDevice getVideoDevice() {
        return videoDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAudioDevice(String audioDeviceName) throws DeviceNotFoundException {
        if (devices.containsKey(audioDeviceName)) {
            this.audioDevice = new CaptureDevice(DeviceType.AUDIO, devices.get(audioDeviceName));
        } else {
            throw new DeviceNotFoundException("audio device: " + audioDeviceName + "not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVideoDevice(String videoDeviceName) throws DeviceNotFoundException {
        if (devices.containsKey(videoDeviceName)) {
            this.videoDevice = new CaptureDevice(DeviceType.VIDEO, devices.get(videoDeviceName));
        } else {
            throw new DeviceNotFoundException("video device: " + videoDeviceName + "not found");
        }
    }

    public Set<String> getVideoDevices() {
        return videoDevices;
    }

    public Set<String> getAudioDevices() {
        return audioDevices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeviceStatistic initDevices() throws DeviceNotFoundException {
        String aDevice = StringUtils.EMPTY;
        String vDevice = StringUtils.EMPTY;
        loadDevices();
        ISetup setup = loadSetup();
        Configuration config = loadConfig();

        try {
            if (setup.getAudioDevice() != null &&
                StringUtils.isNotEmpty(setup.getAudioDevice().getName())) {
                aDevice = setup.getAudioDevice().getName();
            } else {
                aDevice = config.getString(DEFAULT_AUDIO_DEVICE);
                setup.setAudioDevice(new Device(aDevice));

                propertiesManager.save(setup, FILE_NAME);

            }

            if (setup.getVideoDevice() != null &&
                StringUtils.isNotEmpty(setup.getVideoDevice().getName())) {
                vDevice = setup.getVideoDevice().getName();
            } else {
                vDevice = config.getString(DEFAULT_VIDEO_DEVICE);
                setup.setVideoDevice(new Device(vDevice));
                propertiesManager.save(setup, FILE_NAME);
            }

        } catch (PropertiesException e) {
            LOGGER.warn(e.getMessage());
        }


        setAudioDevice(aDevice);
        setVideoDevice(vDevice);


        LOGGER.info("Was found " + devices.size() + " devices.");
        LOGGER.info(audioDevice + " current audio device.");
        LOGGER.info(videoDevice + " current video device.");


        return new DeviceStatistic(devices.size(), audioDevices.size(), videoDevices.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CaptureDeviceInfo getCaptureDeviceInfo(String device) throws DeviceNotFoundException {
        return getDevice(device);
    }

    private void loadDevices() throws DeviceNotFoundException {
        devices = new HashMap();
        videoDevices = new HashSet();
        audioDevices = new HashSet();
        LOGGER.info("------ Init devices ------");
        Vector deviceListVector = CaptureDeviceManager.getDeviceList(ALL_DEVICES);
        if (CollectionUtils.isNotEmpty(deviceListVector)) {
            for (Object device : deviceListVector) {
                if (device instanceof CaptureDeviceInfo) {
                    CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) device;
                    devices.put(deviceInfo.getName(), deviceInfo);
                    if (deviceInfo.toString().toLowerCase().contains("sound") ||
                        deviceInfo.toString().toLowerCase().contains("audio")) {
                        audioDevices.add(deviceInfo.getName());
                    } else if (deviceInfo.toString().toLowerCase().contains("video")) {
                        videoDevices.add(deviceInfo.getName());
                    }
                }
            }
        } else {
            throw new DeviceNotFoundException("No one device was found");
        }
    }

    private ISetup loadSetup() {
        ISetup setup;
        try {
            setup = propertiesManager.read(Setup.class, FILE_NAME);
        } catch (PropertiesException e) {
            LOGGER.warn(e.getMessage());
            throw new IllegalStateException(e);
        }
        return setup;
    }

    private Configuration loadConfig() {
        Configuration config = null;
        try {
            config = new PropertiesConfiguration("device.properties");
        } catch (ConfigurationException e) {
            LOGGER.error("FAILED LOAD PROPERTIES");
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
        return config;
    }

    private CaptureDeviceInfo getDevice(String device) throws DeviceNotFoundException {
        CaptureDeviceInfo captureDeviceInfo = CaptureDeviceManager.getDevice(device);
        if (captureDeviceInfo != null) {
            LOGGER.info("The device: '" + device + "' successfully found and loaded.");
        } else {
            throw new DeviceNotFoundException(getDeviceNotFoundMsg(device));
        }
        return captureDeviceInfo;
    }

    private String getDeviceNotFoundMsg(String device) {
        return MessageFormat.format("The device: ''{0}'' not found.",
            (StringUtils.isNotEmpty(device) ? device : StringUtils.EMPTY));
    }
}
