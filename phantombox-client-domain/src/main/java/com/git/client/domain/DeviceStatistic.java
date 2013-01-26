package com.git.client.domain;

import com.git.client.api.domain.IDeviceStatistic;

import java.text.MessageFormat;

/**
 * Enter class description.
 * <p/>
 * Date: 1/26/13
 * Time: 8:54 PM
 *
 * @author dmgcodevil
 */
public class DeviceStatistic implements IDeviceStatistic {
    private int totalCountDevice;
    private int countAudioDevice;
    private int countVideoDevice;

    public int getTotalCountDevice() {
        return totalCountDevice;
    }

    public void setTotalCountDevice(int totalCountDevice) {
        this.totalCountDevice = totalCountDevice;
    }

    public int getCountAudioDevice() {
        return countAudioDevice;
    }

    public void setCountAudioDevice(int countAudioDevice) {
        this.countAudioDevice = countAudioDevice;
    }

    public int getCountVideoDevice() {
        return countVideoDevice;
    }

    public void setCountVideoDevice(int countVideoDevice) {
        this.countVideoDevice = countVideoDevice;
    }

    public DeviceStatistic(int totalCountDevice, int countAudioDevice, int countVideoDevice) {
        this.totalCountDevice = totalCountDevice;
        this.countAudioDevice = countAudioDevice;
        this.countVideoDevice = countVideoDevice;
    }

    public String getInfo() {
        String info = "Found ''{0}'' devices. Audio devices: ''{1}'', video devices: ''{2}''";
        return MessageFormat.format(info, totalCountDevice, countAudioDevice, countVideoDevice);
    }
}