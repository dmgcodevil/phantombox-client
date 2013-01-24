package com.git.client.api.domain;

/**
 * DeviceType.
 * <p/>
 * Date: 30.11.12
 * Time: 13:06
 *
 * @author rpleshkov
 */
public enum DeviceType {
    AUDIO("audio"),
    VIDEO("video");

    private String value;

    /**
     * Gets value.
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Constructor with parameters.
     *
     * @param value value
     */
    DeviceType(String value) {
        this.value = value;
    }
}
