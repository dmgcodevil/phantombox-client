package com.git.client.domain;

import com.git.client.api.domain.IAudioDevice;
import com.git.client.api.domain.ISetup;
import com.git.client.api.domain.IVideoDevice;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@link ISetup} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 8:29 AM
 */
@XmlRootElement
public class Setup implements ISetup {

    private Set<String> audioDevices = new HashSet();

    private Set<String> videoDevices = new HashSet();

    private IAudioDevice audioDevice = new AudioDevice();

    private IVideoDevice videoDevice = new VideoDevice();


    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAudioDevices() {
        return audioDevices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @XmlElement
    public void setAudioDevices(Set<String> audioDevices) {
        this.audioDevices = audioDevices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getVideoDevices() {
        return videoDevices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @XmlElement
    public void setVideoDevices(Set<String> videoDevices) {
        this.videoDevices = videoDevices;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IAudioDevice getAudioDevice() {
        return audioDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @XmlElement
    public void setAudioDevice(IAudioDevice audioDevice) {
        this.audioDevice = audioDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVideoDevice getVideoDevice() {
        return videoDevice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @XmlElement
    public void setVideoDevice(IVideoDevice videoDevice) {
        this.videoDevice = videoDevice;
    }
}
