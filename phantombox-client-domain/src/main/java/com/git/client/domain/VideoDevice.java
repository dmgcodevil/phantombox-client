package com.git.client.domain;

import com.git.client.api.domain.IVideoDevice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@link IVideoDevice} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 8:31 AM
 */
@XmlRootElement
public class VideoDevice extends Device implements IVideoDevice {

    /**
     * Default constructor.
     */
    public VideoDevice() {
    }

    /**
     * Constructor with parameters.
     *
     * @param name name
     */
    public VideoDevice(String name) {
        super(name);
    }
}
