package com.git.client.webcam.device;

import com.git.client.api.exception.DeviceNotFoundException;
import com.git.client.api.webcam.device.IDeviceManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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

/**
 * {@link IDeviceManager} interface implementation.
 * <p/>
 * Date: 06.11.12
 * Time: 12:21
 *
 * @author rpleshkov
 */
public class DeviceManager implements IDeviceManager {

    private Map<String, CaptureDeviceInfo> devices = new HashMap();

    private Map<String, CaptureDeviceInfo> audioDevices = new HashMap();

    private Map<String, CaptureDeviceInfo> videoDevices = new HashMap();

    // TODO move it to properties file or something that
    private static final String JS_AUDIO_CAPTURE = "JavaSound audio capture";

    private static final String DS_AUDIO_CAPTURE = "DirectSoundCapture";

    private static final String VFM_WDM = "vfw:Microsoft WDM Image Capture (Win32):0";

    private static final Set<String> DEF_AUDIO_DEVICES = new HashSet();

    private static final Set<String> DEF_VIDEO_DEVICES = new HashSet();

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceManager.class);

    static {
        DEF_AUDIO_DEVICES.add(JS_AUDIO_CAPTURE);
        DEF_AUDIO_DEVICES.add(DS_AUDIO_CAPTURE);
        DEF_VIDEO_DEVICES.add(VFM_WDM);
    }


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
    public Map<String, CaptureDeviceInfo> getAudioDevices() {
        return audioDevices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, CaptureDeviceInfo> getVideoDevices() {
        return videoDevices;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void initDevices() {
        devices.clear();
        Vector deviceListVector = CaptureDeviceManager.getDeviceList(null);
        if (CollectionUtils.isNotEmpty(deviceListVector)) {
            for (Object device : deviceListVector) {
                if (device instanceof CaptureDeviceInfo) {
                    CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) device;
                    devices.put(deviceInfo.getName(), deviceInfo);
                }
            }

            audioDevices = new HashMap();
            videoDevices = new HashMap();
            if (MapUtils.isNotEmpty(devices)) {
                for (String device : devices.keySet()) {
                    // TODO refine it
                    if (DEF_AUDIO_DEVICES.contains(device)) {
                        audioDevices.put(device, devices.get(device));
                    } else if (DEF_VIDEO_DEVICES.contains(device)) {
                        videoDevices.put(device, devices.get(device));
                    }
                }
            }

            LOGGER.info("Was found " + deviceListVector.size() + " devices.");
            LOGGER.info(audioDevices.size() + " audio devices.");
            LOGGER.info(videoDevices.size() + " video devices.");

        } else {
            LOGGER.info("No one device was found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CaptureDeviceInfo getCaptureDeviceInfo(String device) throws DeviceNotFoundException {
        return getDevice(device);
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
