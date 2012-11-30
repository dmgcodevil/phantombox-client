package com.git.client.api.webcam.transmitter;

/**
 * Transmission type.
 * <p/>
 * Date: 08.11.12
 * Time: 11:46
 *
 * @author rpleshkov
 */
public enum TransmissionType {
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
    TransmissionType(String value) {
        this.value = value;
    }
}
