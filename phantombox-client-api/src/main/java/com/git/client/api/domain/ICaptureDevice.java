package com.git.client.api.domain;

import javax.media.CaptureDeviceInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Raman_Pliashkou
 * Date: 1/24/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICaptureDevice {

    DeviceType getDeviceType();

    void setDeviceType(DeviceType deviceType);

    CaptureDeviceInfo getCaptureDeviceInfo();

    void setCaptureDeviceInfo(CaptureDeviceInfo captureDeviceInfo);
}
