package com.git.client.api.webcam.device;


import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.domain.IDeviceStatistic;
import com.git.client.api.exception.DeviceNotFoundException;

import java.util.Map;
import java.util.Set;
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
     * Init devices. Finds all devices which are registered in system and available through jmf.
     * If setup.xml not exist or doesn't have info, then load default devices from properties and
     * create ne setup, set devices and save.
     * <p/>
     * <p/>
     * Caution. Run this method immediately after  creation Device Manager.
     *
     * @return {@link IDeviceStatistic}
     * @throws DeviceNotFoundException {@link DeviceNotFoundException}
     */
    IDeviceStatistic initDevices() throws DeviceNotFoundException;

    /**
     * Set audio device.
     *
     * @param audioDeviceName audio device
     * @throws DeviceNotFoundException {@link DeviceNotFoundException}
     */
    void setAudioDevice(String audioDeviceName) throws DeviceNotFoundException;

    /**
     * Set video device.
     *
     * @param videoDeviceName video device
     * @throws DeviceNotFoundException {@link DeviceNotFoundException}
     */
    void setVideoDevice(String videoDeviceName) throws DeviceNotFoundException;

    /**
     * Gets video devices.
     *
     * @return video devices
     */
    Set<String> getVideoDevices();

    /**
     * Gets audio devices.
     *
     * @return audio devices
     */
    Set<String> getAudioDevices();

    /**
     * Get capture device info.
     *
     * @param device device
     * @return {@link CaptureDeviceInfo}
     * @throws DeviceNotFoundException {@link DeviceNotFoundException}
     */
    CaptureDeviceInfo getCaptureDeviceInfo(String device) throws DeviceNotFoundException;
}
