package com.git.client.domain;

import com.git.client.api.domain.DeviceType;
import com.git.client.api.domain.ICaptureDevice;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.media.CaptureDeviceInfo;

/**
 * {@link ICaptureDevice} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 8:30 AM
 */
public class CaptureDevice implements ICaptureDevice {

    private DeviceType deviceType;

    private CaptureDeviceInfo captureDeviceInfo;

    /**
     * Default constructor.
     */
    public CaptureDevice() {
    }

    /**
     * Constructor with parameters.
     *
     * @param deviceType        {@link DeviceType}
     * @param captureDeviceInfo {@link CaptureDeviceInfo}
     */
    public CaptureDevice(DeviceType deviceType, CaptureDeviceInfo captureDeviceInfo) {
        this.deviceType = deviceType;
        this.captureDeviceInfo = captureDeviceInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CaptureDeviceInfo getCaptureDeviceInfo() {
        return captureDeviceInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCaptureDeviceInfo(CaptureDeviceInfo captureDeviceInfo) {
        this.captureDeviceInfo = captureDeviceInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaptureDevice)) {
            return false;
        }

        CaptureDevice that = (CaptureDevice) o;
        return new EqualsBuilder()
            .appendSuper(super.equals(o))
            .append(deviceType, that.deviceType)
            .append(captureDeviceInfo, that.captureDeviceInfo)
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(deviceType)
            .append(captureDeviceInfo)
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("deviceType", deviceType)
            .append("captureDeviceInfo", captureDeviceInfo)
            .toString();
    }
}
