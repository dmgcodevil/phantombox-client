package com.git.client.webcam.device;

import static com.git.domain.api.Constants.DSC;
import static com.git.domain.api.Constants.JSC;
import static com.git.domain.api.Constants.VFM_WDM;
import com.git.client.api.domain.DeviceType;
import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.exception.DeviceNotFoundException;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.domain.CaptureDevice;
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

    private static final Format ALL_DEVICES = null;

    private static final Set<String> DEF_AUDIO_DEVICES = new HashSet();

    private static final Set<String> DEF_VIDEO_DEVICES = new HashSet();

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceManager.class);

    static {
        DEF_AUDIO_DEVICES.add(JSC);
        DEF_AUDIO_DEVICES.add(DSC);
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
    public void setDevice(Map<String, CaptureDeviceInfo> devices) {
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
    public void initDevices() {
        devices = new HashMap();
        Vector deviceListVector = CaptureDeviceManager.getDeviceList(ALL_DEVICES);
        if (CollectionUtils.isNotEmpty(deviceListVector)) {
            for (Object device : deviceListVector) {
                if (device instanceof CaptureDeviceInfo) {
                    CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) device;
                    devices.put(deviceInfo.getName(), deviceInfo);
                }
            }

            // TODO load current devices from properties
            if (MapUtils.isNotEmpty(devices)) {
                if (devices.containsKey(VFM_WDM)) {
                    videoDevice = new CaptureDevice(DeviceType.VIDEO, devices.get(VFM_WDM));
                }

                if (devices.containsKey(DSC)) {
                    audioDevice = new CaptureDevice(DeviceType.AUDIO, devices.get(DSC));
                }
            }


            LOGGER.info("Was found " + deviceListVector.size() + " devices.");
            LOGGER.info(audioDevice + " current audio device.");
            LOGGER.info(videoDevice + " current video device.");

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
