package com.git.client.webcam.processor;

import com.git.client.api.exception.ProcessorCreationException;
import com.git.client.api.webcam.processor.IProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Dimension;
import java.io.IOException;
import javax.media.Codec;
import javax.media.Control;
import javax.media.Controller;
import javax.media.ControllerClosedEvent;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Format;
import javax.media.Manager;
import javax.media.NoProcessorException;
import javax.media.Owned;
import javax.media.Player;
import javax.media.Processor;
import javax.media.control.QualityControl;
import javax.media.control.TrackControl;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

/**
 * {@link IProcessorFactory} interface implementation.
 * <p/>
 * Date: 06.11.12
 * Time: 15:06
 *
 * @author rpleshkov
 */
public class ProcessorFactory implements IProcessorFactory {

    private static final float JPEG_QUALITY = 0.5f;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorFactory.class);

    private Integer stateLock = new Integer(0);

    private boolean failed = false;

    // TODO refactor it !!!
    private static final int RESOLUTION = 8;
    private static final int ZERO = 0;
    private static final int WIDTH_128 = 128;
    private static final int WIDTH_176 = 176;
    private static final int WIDTH_352 = 352;
    private static final int HEIGHT_96 = 96;
    private static final int HEIGHT_144 = 144;
    private static final int HEIGHT_288 = 288;

    private Integer getStateLock() {
        return stateLock;
    }

    private void setFailed() {
        failed = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Processor createProcessor(DataSource dataSource) throws ProcessorCreationException {
        Processor processor = null;
        try {
            processor = Manager.createProcessor(dataSource);
        } catch (NoProcessorException ex) {
            throw new ProcessorCreationException("Couldn't create processor", ex);
        } catch (IOException ex) {
            throw new ProcessorCreationException("IOException creating processor", ex);
        }

        configure(processor);
        realize(processor);
        setJPEGQuality(processor, JPEG_QUALITY);
        LOGGER.info("Processor is configured and realized.");
        return processor;
    }

    private void configure(Processor processor) throws ProcessorCreationException {
        boolean result = waitForState(processor, Processor.Configured);
        if (result) {
            configureTracks(processor);
            LOGGER.info("processor successfully configured");
        } else {
            throw new ProcessorCreationException("Couldn't configure processor");
        }
    }

    private void configureTracks(Processor processor) throws ProcessorCreationException {
        int length = 1;
        TrackControl[] tracks = processor.getTrackControls();
        if (tracks != null && tracks.length >= length) {
            // Set the output content descriptor to RAW_RTP
            // This will limit the supported formats reported from
            // Track.getSupportedFormats to only valid RTP formats.
            ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW_RTP);
            processor.setContentDescriptor(cd);
            boolean atLeastOneTrack = false;

            // Program the tracks.
            for (int i = 0; i < tracks.length; i++) {
                if (tracks[i].isEnabled()) {
                    atLeastOneTrack = configureFormat(tracks[i]);
                }
            }
            if (!atLeastOneTrack) {
                throw new ProcessorCreationException("Couldn't set any of the " +
                    "tracks to a valid RTP format");
            }

        } else {
            throw new ProcessorCreationException("Couldn't find tracks in processor.");
        }
    }

    // TODO create format configurator. visitor ?
    private boolean configureFormat(TrackControl trackControl) {
        boolean configuredVideo = false;
        boolean configuredAudio = false;
        if (trackControl.getFormat() instanceof VideoFormat) {
            configuredVideo = configureVideoFormat(trackControl);
        }
        if (trackControl.getFormat() instanceof AudioFormat) {
            configuredAudio = configureAudioFormat(trackControl);
        }
        return (configuredVideo || configuredAudio);
    }

    // TODO implement manual setup format.
    // now choose first of supported format for track. it's very strict limit.
    private boolean configureVideoFormat(TrackControl trackControl) {
        boolean configured = false;
        int defFormat = 0; // refactor it
        int length = 0;
        Format[] supported = trackControl.getSupportedFormats();
        Format format = trackControl.getFormat();
        if (supported.length > length) {
            if (supported[defFormat] instanceof VideoFormat) {
                trackControl.setFormat(checkForVideoSizes(format, supported[defFormat]));
                LOGGER.info("Set " + trackControl.getFormat() + " format for " + trackControl);
                configured = true;
            }
        }
        return configured;
    }

    // TODO implement manual setup format.
    private boolean configureAudioFormat(TrackControl trackControl) {
        boolean configured = false;
        AudioFormat ulawFormat = new AudioFormat(AudioFormat.DVI_RTP);
        trackControl.setFormat(ulawFormat);
        configured = true;
        return configured;
    }

    private void realize(Processor processor) throws ProcessorCreationException {
        boolean result = waitForState(processor, Controller.Realized);
        if (result) {
            LOGGER.info("processor successfully realized");
        } else {
            throw new ProcessorCreationException("Couldn't realize processor.");
        }
    }

    private synchronized boolean waitForState(Processor p, int state) {
        p.addControllerListener(new StateListener());
        failed = false;

        // Call the required method on the processor
        if (state == Processor.Configured) {
            p.configure();
        } else if (state == Processor.Realized) {
            p.realize();
        }

        // Wait until we get an event that confirms the
        // success of the method, or a failure event.
        // See StateListener inner class
        while (p.getState() < state && !failed) {
            synchronized (getStateLock()) {
                try {
                    LOGGER.info("try to process state: " + state);
                    getStateLock().wait();
                } catch (InterruptedException ie) {
                    return false;
                }
            }
        }

        return failed ? false : true;
    }

    /**
     * For JPEG and H263, we know that they only work for particular
     * sizes.  So we'll perform extra checking here to make sure they
     * are of the right sizes.
     */
    private Format checkForVideoSizes(Format original, Format supported) {
        // TODO refactor it !!!
        int width, height;
        Dimension size = ((VideoFormat) original).getSize();
        Format jpegFmt = new Format(VideoFormat.JPEG_RTP);
        Format h263Fmt = new Format(VideoFormat.H263_RTP);

        if (supported.matches(jpegFmt)) {
            // For JPEG, make sure width and height are divisible by 8.
            width = (size.width % RESOLUTION == ZERO ? size.width :
                (int) (size.width / RESOLUTION) * RESOLUTION);
            height = (size.height % RESOLUTION == ZERO ? size.height :
                (int) (size.height / RESOLUTION) * RESOLUTION);
        } else if (supported.matches(h263Fmt)) {
            // For H.263, we only support some specific sizes.
            if (size.width < WIDTH_128) {
                width = WIDTH_128;
                height = HEIGHT_96;
            } else if (size.width < WIDTH_176) {
                width = WIDTH_176;
                height = HEIGHT_144;
            } else {
                width = WIDTH_352;
                height = HEIGHT_288;
            }
        } else {
            // We don't know this particular format.  We'll just
            // leave it alone then.
            return supported;
        }

        return (new VideoFormat(null,
            new Dimension(width, height),
            Format.NOT_SPECIFIED,
            null,
            Format.NOT_SPECIFIED)).intersects(supported);
    }

    /**
     * Setting the encoding quality to the specified value on the JPEG encoder.
     * 0.5 is a good default.
     */
    private void setJPEGQuality(Player p, float val) {
        Control[] cs = p.getControls();
        QualityControl qc = null;
        VideoFormat jpegFmt = new VideoFormat(VideoFormat.JPEG);

        // Loop through the controls to find the Quality control for
        // the JPEG encoder.
        for (int i = 0; i < cs.length; i++) {

            if (cs[i] instanceof QualityControl && cs[i] instanceof Owned) {
                Object owner = ((Owned) cs[i]).getOwner();

                // Check to see if the owner is a Codec.
                // Then check for the output format.
                if (owner instanceof Codec) {
                    Format[] fmts = ((Codec) owner).getSupportedOutputFormats(null);
                    for (int j = 0; j < fmts.length; j++) {
                        if (fmts[j].matches(jpegFmt)) {
                            qc = (QualityControl) cs[i];
                            qc.setQuality(val);
                            LOGGER.debug("- Setting quality to " +
                                val + " on " + qc);
                            break;
                        }
                    }
                }
                if (qc != null) {
                    break;
                }
            }
        }
    }


    private class StateListener implements ControllerListener {

        public void controllerUpdate(ControllerEvent ce) {

            // If there was an error during configure or
            // realize, the processor will be closed
            if (ce instanceof ControllerClosedEvent) {
                setFailed();
            }

            // All controller events, send a notification
            // to the waiting thread in waitForState method.
            if (ce instanceof ControllerEvent) {
                synchronized (getStateLock()) {
                    getStateLock().notifyAll();
                }
            }
        }
    }
}
