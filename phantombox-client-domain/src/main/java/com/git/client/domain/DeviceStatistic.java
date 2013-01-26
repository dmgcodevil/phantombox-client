package com.git.client.domain;

import com.git.client.api.domain.IDeviceStatistic;

import java.text.MessageFormat;

/**
 * {@link IDeviceStatistic} interface implementation.
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalCountDevice() {
        return totalCountDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTotalCountDevice(int totalCountDevice) {
        this.totalCountDevice = totalCountDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCountAudioDevice() {
        return countAudioDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCountAudioDevice(int countAudioDevice) {
        this.countAudioDevice = countAudioDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCountVideoDevice() {
        return countVideoDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCountVideoDevice(int countVideoDevice) {
        this.countVideoDevice = countVideoDevice;
    }

    /**
     * Constructor.
     *
     * @param totalCountDevice totalCountDevice
     * @param countAudioDevice countAudioDevice
     * @param countVideoDevice countVideoDevice
     */
    public DeviceStatistic(int totalCountDevice, int countAudioDevice, int countVideoDevice) {
        this.totalCountDevice = totalCountDevice;
        this.countAudioDevice = countAudioDevice;
        this.countVideoDevice = countVideoDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        String info = "Found ''{0}'' devices. Audio devices: ''{1}'', video devices: ''{2}''";
        return MessageFormat.format(info, totalCountDevice, countAudioDevice, countVideoDevice);
    }
}