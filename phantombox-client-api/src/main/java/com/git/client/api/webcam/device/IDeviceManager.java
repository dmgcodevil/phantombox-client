package com.git.client.api.webcam.device;


import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.exception.DeviceNotFoundException;

import java.util.Map;
import javax.media.CaptureDeviceInfo;

/**
 * IDevice manager.
 * <p/>
 * Date: 08.11.12
 * Time: 15:27
 *
 * @author rpleshkov
 */
public interface IDeviceManager {

    /**
     * Gets devices.
     *
     * @return Map
     *         key - device name
     *         value -  {@link CaptureDeviceInfo}
     */
    public Map<String, CaptureDeviceInfo> getDevices();


    /**
     * Sets devices.
     *
     * @param devices devices Map
     *                key - device name
     *                value -  {@link CaptureDeviceInfo}
     */
    public void setDevices(Map<String, CaptureDeviceInfo> devices);

    /**
     * Gets audio device.
     *
     * @return {@link ICaptureDevice}
     */
    public ICaptureDevice getAudioDevice();

    /**
     * Gets video device.
     *
     * @return {@link ICaptureDevice}
     */
    public ICaptureDevice getVideoDevice();


    /**
     * FInit devices. Finds all devices which are registered in system and available through jmf.
     */
    void initDevices();

    /**
     * Get capture device info.
     *
     * @param device device
     * @return {@link CaptureDeviceInfo}
     * @throws DeviceNotFoundException {@link DeviceNotFoundException}
     */
    CaptureDeviceInfo getCaptureDeviceInfo(String device) throws DeviceNotFoundException;
}
