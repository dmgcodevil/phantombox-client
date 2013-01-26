package com.git.client.api.domain;

/**
 * Enter class description.
 * <p/>
 * Date: 1/26/13
 * Time: 8:55 PM
 *
 * @author dmgcodevil
 */
public interface IDeviceStatistic {

    int getTotalCountDevice();

    void setTotalCountDevice(int totalCountDevice);

    int getCountAudioDevice();

    void setCountAudioDevice(int countAudioDevice);

    int getCountVideoDevice();

    void setCountVideoDevice(int countVideoDevice);

    String getInfo();
}
