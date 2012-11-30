package com.git.client.api.domain;

import java.util.Set;

/**
 * ISetup interface.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 8:29 AM
 */

public interface ISetup {

    /**
     * Gets audio devices.
     *
     * @return audio devices
     */
    Set<String> getAudioDevices();

    /**
     * Set audio devices.
     *
     * @param audioDevices audio devices
     */
    void setAudioDevices(Set<String> audioDevices);

    /**
     * Gets video devices.
     *
     * @return video devices
     */
    Set<String> getVideoDevices();

    /**
     * Sets video devices.
     *
     * @param videoDevices video devices
     */
    void setVideoDevices(Set<String> videoDevices);

    /**
     * Gets audio device.
     *
     * @return {@link IAudioDevice}
     */
    IAudioDevice getAudioDevice();

    /**
     * Sets audio device.
     *
     * @param audioDevice {@link IAudioDevice}
     */
    void setAudioDevice(IAudioDevice audioDevice);

    /**
     * Get video device.
     *
     * @return {@link IVideoDevice}
     */
    IVideoDevice getVideoDevice();

    /**
     * Set video device.
     *
     * @param videoDevice {@link IVideoDevice}
     */
    void setVideoDevice(IVideoDevice videoDevice);
}
