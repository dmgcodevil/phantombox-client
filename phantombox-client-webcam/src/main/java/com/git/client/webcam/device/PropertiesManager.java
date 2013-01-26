package com.git.client.webcam.device;

import com.git.client.api.exception.PropertiesException;
import com.git.client.api.webcam.device.IPropertiesManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.xml.bind.JAXBException;

/**
 * {@link IPropertiesManager} implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 1:38 PM
 */
public class PropertiesManager implements IPropertiesManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesManager.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void save(T obj, String fileName) throws PropertiesException {
        try {
            JAXBProvider.create(obj, fileName);
        } catch (JAXBException ex) {
            throw new PropertiesException("Error saving properties.", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T read(Class cl, String filename) throws PropertiesException {
        T obj = null;
        try {
            obj = JAXBProvider.read(cl, filename);
        } catch (IOException | JAXBException ex) {
            try {
                LOGGER.error("File properties not found or has wrong format.");
                save(cl.newInstance(), filename);
                LOGGER.info("Was created default properties and saved");
                obj = read(cl, filename);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new PropertiesException(e);
            }
        }
        return obj;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String filename) {
        JAXBProvider.delete(filename);
    }
}
