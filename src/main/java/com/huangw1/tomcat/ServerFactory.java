package com.huangw1.tomcat;

import com.huangw1.tomcat.config.ServerConfig;
import com.huangw1.tomcat.impl.SimpleServer;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class ServerFactory {

    public static Server getServer(ServerConfig config) {
        return new SimpleServer(config);
    }
}
