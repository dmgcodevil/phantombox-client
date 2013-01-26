package com.git.client.api.domain;

/**
 * IDeviceStatistic interface.
 * <p/>
 * Date: 1/26/13
 * Time: 8:55 PM
 *
 * @author dmgcodevil
 */
public interface IDeviceStatistic {

    /**
     * Gets total count device.
     *
     * @return total count device
     */
    int getTotalCountDevice();

    /**
     * Sets total count device.
     *
     * @param totalCountDevice total count device
     */
    void setTotalCountDevice(int totalCountDevice);

    /**
     * Gets count audio device.
     *
     * @return count audio device
     */
    int getCountAudioDevice();

    /**
     * Sets count audio device.
     *
     * @param countAudioDevice count audio device
     */
    void setCountAudioDevice(int countAudioDevice);

    /**
     * Gets count video device.
     *
     * @return count video device
     */
    int getCountVideoDevice();

    /**
     * Gets count video device.
     *
     * @param countVideoDevice count video device
     */
    void setCountVideoDevice(int countVideoDevice);

    /**
     * Gets info.
     *
     * @return info
     */
    String getInfo();
}
