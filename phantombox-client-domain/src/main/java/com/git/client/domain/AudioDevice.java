package com.git.client.domain;

import com.git.client.api.domain.IAudioDevice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@link IAudioDevice} interface implementation..
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 8:30 AM
 */
@XmlRootElement
public class AudioDevice extends Device implements IAudioDevice {

    /**
     * Default constructor.
     */
    public AudioDevice() {
    }

    /**
     * Constructor with parameters.
     *
     * @param name name
     */
    public AudioDevice(String name) {
        super(name);
    }
}
