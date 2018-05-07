package com.huangw1.tomcat.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class IoUtil {

    private static Logger logger = LoggerFactory.getLogger(IoUtil.class);

    public static void closeQuietly(Closeable closeable) {
        if(closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
