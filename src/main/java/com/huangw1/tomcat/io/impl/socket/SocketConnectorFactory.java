package com.huangw1.tomcat.io.impl.socket;

import com.huangw1.tomcat.event.impl.SocketEventListener;
import com.huangw1.tomcat.io.ConnectorFactory;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class SocketConnectorFactory implements ConnectorFactory {

    private final SocketConnectorConfig socketConnectorConfig;
    private final SocketEventListener socketEventListener;

    public SocketConnectorFactory(SocketConnectorConfig socketConnectorConfig, SocketEventListener socketEventListener) {
        this.socketConnectorConfig = socketConnectorConfig;
        this.socketEventListener = socketEventListener;
    }

    @Override
    public SocketConnector getConnector() {
        return new SocketConnector(socketConnectorConfig.getPort(), socketEventListener);
    }
}
