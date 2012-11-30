package com.git.client.webcam.locator;


import com.git.client.api.exception.MediaLocatorCreationException;
import com.git.client.api.webcam.locator.IMediaLocatorFactory;

import org.apache.commons.lang3.StringUtils;

import javax.media.CaptureDeviceInfo;
import javax.media.MediaLocator;

/**
 * {@link IMediaLocatorFactory} interface implementation.
 * <p/>
 * Date: 05.11.12
 * Time: 19:07
 *
 * @author rpleshkov
 */
public class MediaLocatorFactory implements IMediaLocatorFactory {

    // TODO verify it
    private static final String VFM_WDM = "vfw://";

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaLocator createMediaLocator(String locator) throws MediaLocatorCreationException {
        MediaLocator mediaLocator = null;
        if (StringUtils.isNotEmpty(locator)) {
            mediaLocator = new MediaLocator(locator);
        } else {
            throw new MediaLocatorCreationException("Can't create media locator.");
        }
        return mediaLocator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaLocator createMediaLocator(CaptureDeviceInfo deviceInfo)
        throws MediaLocatorCreationException {
        MediaLocator mediaLocator = null;
        if (deviceInfo != null) {
            mediaLocator = deviceInfo.getLocator();
        } else {
            throw new MediaLocatorCreationException("Can't create media locator.");
        }
        return mediaLocator;
    }

}
