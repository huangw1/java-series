package com.huangw1.tomcat;

import com.huangw1.tomcat.config.ServerConfig;
import com.huangw1.tomcat.impl.SimpleServer;
import com.huangw1.tomcat.io.Connector;
import com.huangw1.tomcat.io.impl.socket.SocketConnectorConfig;
import com.huangw1.tomcat.io.impl.socket.SocketConnectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class ServerFactory {

    public static Server getServer(ServerConfig serverConfig) {
        List<Connector> connectorList = new ArrayList<>();
        connectorList.add(new SocketConnectorFactory(new SocketConnectorConfig(serverConfig.getPort())).getConnector());
        return new SimpleServer(serverConfig, connectorList);
    }
}
