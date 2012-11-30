package com.git.client.api.webcam.transmitter;

import com.git.client.api.exception.TransmitterException;

import javax.media.protocol.DataSource;

/**
 * Transmitter factory.
 * <p/>
 * User: dmgcodevil
 * Date: 11/7/12
 * Time: 10:26 AM
 */
public interface ITransmitterFactory {

    /**
     * Creates transmitter.
     *
     * @param dataOutput {@link DataSource}
     * @param ipAddress  ip address
     * @param port       port
     * @param type       {@link TransmissionType}
     * @throws TransmitterException {@link TransmitterException}
     */
    void createTransmitter(DataSource dataOutput, String ipAddress, int port, TransmissionType type)
        throws TransmitterException;

    /**
     * Dispose transmitter.
     *
     * @param ipAddress ip address
     * @param port      port
     * @param type      {@link TransmissionType}
     * @throws TransmitterException {@link TransmitterException}
     */
    void disposeTransmitter(String ipAddress, int port, TransmissionType type)
        throws TransmitterException;
}
