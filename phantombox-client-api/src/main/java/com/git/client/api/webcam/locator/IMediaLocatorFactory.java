package com.git.client.api.webcam.locator;


import com.git.client.api.exception.MediaLocatorCreationException;

import javax.media.CaptureDeviceInfo;
import javax.media.MediaLocator;

/**
 * Enter class description.
 * <p/>
 * Date: 08.11.12
 * Time: 15:32
 *
 * @author rpleshkov
 */
public interface IMediaLocatorFactory {

    /**
     * Create media locator.
     *
     * @param locator locator
     * @return {@link javax.media.MediaLocator}
     * @throws MediaLocatorCreationException {@link MediaLocatorCreationException}
     */
    MediaLocator createMediaLocator(String locator) throws MediaLocatorCreationException;

    /**
     * Create media locator.
     *
     * @param deviceInfo {@link javax.media.CaptureDeviceInfo}
     * @return {@link MediaLocator}
     * @throws MediaLocatorCreationException {@link MediaLocatorCreationException}
     */
    MediaLocator createMediaLocator(CaptureDeviceInfo deviceInfo)
        throws MediaLocatorCreationException;
}
