package com.git.client.webcam.device;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 1:35 PM
 */
public class JAXBProvider {

    private JAXBProvider() {
        throw new IllegalAccessError();
    }

    /**
     * Creates properties.
     *
     * @param obj      object
     * @param fileName file name
     * @param <T>      type
     * @throws JAXBException {@link JAXBException}
     */
    public static <T> void create(T obj, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(obj, new File(fileName));
    }

    /**
     * Reads properties for file.
     *
     * @param cl       class
     * @param filename file name
     * @param <T>      type
     * @return object
     * @throws IOException   {@link IOException}
     * @throws JAXBException {@link JAXBException}
     */
    public static <T> T read(Class cl, String filename) throws IOException, JAXBException {
        T obj = null;
        JAXBContext jaxbContext = JAXBContext.newInstance(cl);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        File file = new File(filename);
        if (file.exists()) {
            obj = (T) jaxbUnmarshaller.unmarshal(file);
        } else {
            throw new IOException("file not found.");
        }
        return obj;
    }


    /**
     * Delete file properties.
     *
     * @param filename file name
     * @throws IllegalArgumentException {@link IllegalArgumentException}
     */
    public static void delete(String filename) throws IllegalArgumentException {
        File f = new File(filename);

        if (!f.exists()) {
            throw new IllegalArgumentException(
                "Delete: no such file or directory: " + filename);
        }

        if (!f.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: "
                + filename);
        }

        if (f.isDirectory()) {
            String[] files = f.list();
            if (files.length > 0) {
                throw new IllegalArgumentException(
                    "Delete: directory not empty: " + filename);
            }
        }

        boolean success = f.delete();
        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }
    }
}
