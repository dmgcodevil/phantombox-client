package com.git.client.domain;

import com.git.client.api.domain.DeviceType;
import com.git.client.api.domain.ICaptureDevice;

import javax.media.CaptureDeviceInfo;

/**
 * Capture device.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 8:30 AM
 */
public class CaptureDevice implements ICaptureDevice {

    private DeviceType deviceType;

    private CaptureDeviceInfo captureDeviceInfo;

    public CaptureDevice() {
    }

    public CaptureDevice(DeviceType deviceType, CaptureDeviceInfo captureDeviceInfo) {
        this.deviceType = deviceType;
        this.captureDeviceInfo = captureDeviceInfo;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public CaptureDeviceInfo getCaptureDeviceInfo() {
        return captureDeviceInfo;
    }

    public void setCaptureDeviceInfo(CaptureDeviceInfo captureDeviceInfo) {
        this.captureDeviceInfo = captureDeviceInfo;
    }

    @Override
    public String toString() {
        return "CaptureDevice{" +
            "device type=" + deviceType +
            ", capture device info=" + captureDeviceInfo +
            '}';
    }

    // TODO create hashcode and equals !!!
}
