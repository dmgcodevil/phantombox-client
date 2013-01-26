package com.git.client.api.webcam.device;


import com.git.client.api.exception.PropertiesException;

/**
 * IPropertiesManager interface.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 1:29 PM
 */
public interface IPropertiesManager {

    /**
     * Save properties.
     *
     * @param obj      object
     * @param fileName file name
     * @param <T>      type
     * @throws PropertiesException {@link PropertiesException}
     */
    <T> void save(T obj, String fileName) throws PropertiesException;

    /**
     * Read properties.
     *
     * @param cl       Class
     * @param filename file name
     * @param <T>      type
     * @return object
     * @throws PropertiesException {@link PropertiesException}
     */
    <T> T read(Class cl, String filename) throws PropertiesException;

    /**
     * Delete properties file.
     *
     * @param filename file name
     */
    @Deprecated
    void delete(String filename);
}
