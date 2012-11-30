package com.git.client.api.webcam.datasource;


import com.git.client.api.exception.DataSourceCreationException;

import javax.media.MediaLocator;
import javax.media.protocol.DataSource;

/**
 * Data source factory - creates data source.
 * <p/>
 * Date: 08.11.12
 * Time: 15:29
 *
 * @author rpleshkov
 */
public interface IDataSourceFactory {

    /**
     * Creates data source.
     *
     * @param mediaLocator {@link MediaLocator}
     * @return {@link DataSource}
     * @throws DataSourceCreationException {@link DataSourceCreationException}
     */
    DataSource createDataSource(MediaLocator mediaLocator) throws DataSourceCreationException;
}
