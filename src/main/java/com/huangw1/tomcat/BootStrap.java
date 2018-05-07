package com.huangw1.tomcat;

import com.huangw1.tomcat.config.ServerConfig;

import java.io.IOException;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class BootStrap {

    public static void main(String[] args) throws IOException {
        ServerConfig serverConfig = new ServerConfig();
        Server server = ServerFactory.getServer(serverConfig);
        server.start();
    }
}
