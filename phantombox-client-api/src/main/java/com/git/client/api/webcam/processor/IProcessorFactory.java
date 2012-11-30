package com.git.client.api.webcam.processor;


import com.git.client.api.exception.ProcessorCreationException;

import javax.media.Processor;
import javax.media.protocol.DataSource;

/**
 * Processor factory.
 * <p/>
 * Date: 08.11.12
 * Time: 15:15
 *
 * @author rpleshkov
 */
public interface IProcessorFactory {

    /**
     * Creates processor.
     *
     * @param dataSource {@link DataSource}
     * @return {@link Processor}
     * @throws ProcessorCreationException {@link ProcessorCreationException}
     */
    Processor createProcessor(DataSource dataSource) throws ProcessorCreationException;
}
