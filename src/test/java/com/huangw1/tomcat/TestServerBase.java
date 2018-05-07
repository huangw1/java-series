package com.huangw1.tomcat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by huangw1 on 2018/5/7.
 */
public abstract class TestServerBase {

    private static Logger logger = LoggerFactory.getLogger(TestServerBase.class);

    public void startServer(Server server) {
        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }).start();
    }

    public void waitServerStart(Server server) {
        while(server.getStatus().equals(ServerStatus.STOP)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
