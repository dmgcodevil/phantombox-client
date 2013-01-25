package com.git.client.api.domain;

import javax.media.CaptureDeviceInfo;

/**
 * ICaptureDevice.
 * <p/>
 * Date: 30.11.12
 * Time: 13:06
 *
 * @author rpleshkov
 */
public interface ICaptureDevice {

    /**
     * Gets device type.
     *
     * @return device type
     */
    DeviceType getDeviceType();

    /**
     * Sets device type.
     *
     * @param deviceType device type
     */
    void setDeviceType(DeviceType deviceType);

    /**
     * Gets capture device info.
     *
     * @return capture device info
     */
    CaptureDeviceInfo getCaptureDeviceInfo();

    /**
     * Sets capture device info.
     *
     * @param captureDeviceInfo capture device info
     */
    void setCaptureDeviceInfo(CaptureDeviceInfo captureDeviceInfo);
}
