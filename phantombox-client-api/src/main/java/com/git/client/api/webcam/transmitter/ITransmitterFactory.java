package com.git.client.api.webcam.transmitter;

import com.git.client.api.domain.ICaptureDevice;
import com.git.client.api.exception.TransmitterException;
import com.git.domain.api.IConnection;

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
     * @param connection {@link IConnection}
     * @param device     {@link ICaptureDevice}
     * @throws TransmitterException {@link TransmitterException}
     */
    void createTransmitter(DataSource dataOutput, IConnection connection, ICaptureDevice device)
        throws TransmitterException;

    /**
     * Dispose transmitter.
     *
     * @param connection {@link IConnection}
     * @param device     {@link ICaptureDevice}
     * @throws TransmitterException {@link TransmitterException}
     */
    void disposeTransmitter(IConnection connection, ICaptureDevice device)
        throws TransmitterException;

    /**
     * Dispose transmitter.
     *
     * @throws TransmitterException {@link TransmitterException}
     */
    void disposeTransmitter() throws TransmitterException;
}
