package com.huangw1.tomcat.io.impl.socket;

import com.huangw1.tomcat.io.Connector;
import com.huangw1.tomcat.io.ConnectorFactory;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class SocketConnectorFactory implements ConnectorFactory {

    private final SocketConnectorConfig socketConnectorConfig;

    public SocketConnectorFactory(SocketConnectorConfig socketConnectorConfig) {
        this.socketConnectorConfig = socketConnectorConfig;
    }

    @Override
    public Connector getConnector() {
        return new SocketConnector(socketConnectorConfig.getPort());
    }
}
