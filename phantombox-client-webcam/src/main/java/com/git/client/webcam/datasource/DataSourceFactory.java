package com.git.client.webcam.datasource;


import com.git.client.api.exception.DataSourceCreationException;
import com.git.client.api.webcam.datasource.IDataSourceFactory;

import java.io.IOException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSourceException;
import javax.media.protocol.DataSource;

/**
 * {@link IDataSourceFactory} interface implementation.
 * <p/>
 * Date: 06.11.12
 * Time: 16:36
 *
 * @author rpleshkov
 */
public class DataSourceFactory implements IDataSourceFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSource createDataSource(MediaLocator mediaLocator)
        throws DataSourceCreationException {
        DataSource dataSource = null;
        try {
            dataSource = Manager.createDataSource(mediaLocator);
        } catch (NoDataSourceException ex) {
            throw new DataSourceCreationException("Couldn't create DataSource", ex);
        } catch (IOException ex) {
            throw new DataSourceCreationException("IOException creating DataSource", ex);
        }

        return dataSource;
    }

}