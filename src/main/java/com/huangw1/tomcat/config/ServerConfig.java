package com.huangw1.tomcat.config;

/**
 * Created by huangw1 on 2018/5/7.
 */

/**
 *
 */
public class ServerConfig {

    public static final int DEFAULT_PORT = 8080;

    private final int port;

    public ServerConfig() {
        this.port = DEFAULT_PORT;
    }

    public ServerConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
