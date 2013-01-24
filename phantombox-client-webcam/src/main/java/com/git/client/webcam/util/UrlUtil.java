package com.git.client.webcam.util;

import com.git.client.api.domain.DeviceType;

/**
 * Url utils.
 * <p/>
 * Date: 08.11.12
 * Time: 19:15
 *
 * @author rpleshkov
 */
public class UrlUtil {

    public static final String RTP = "rtp://";

    private UrlUtil() {
        throw new AssertionError();
    }

    /**
     * Builds url.
     *
     * @param trType    (rtp, file)
     * @param ipAddress ip address
     * @param port      port
     * @param type      {@@link DeviceType}
     * @return url
     */
    public static String buildUrl(String trType, String ipAddress,
                                  int port, DeviceType type) {
        StringBuilder builder = new StringBuilder(trType);
        builder.append(ipAddress);
        builder.append(":");
        builder.append(port);
        builder.append("/");
        builder.append(type.getValue());
        return builder.toString();
    }
}
