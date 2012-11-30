package com.git.client.domain;

import com.git.client.api.domain.IDevice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@link IDevice} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 8:31 AM
 */
@XmlRootElement
public class Device implements IDevice {

    private String name;

    /**
     * Default constructor.
     */
    public Device() {
    }

    /**
     * Constructor with parameters.
     *
     * @param name name
     */
    public Device(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
}
