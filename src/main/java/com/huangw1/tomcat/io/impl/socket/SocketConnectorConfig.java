package com.huangw1.tomcat.io.impl.socket;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class SocketConnectorConfig {

    private final int port;

    public SocketConnectorConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
