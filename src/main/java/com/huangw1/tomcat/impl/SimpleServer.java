package com.huangw1.tomcat.impl;

import com.huangw1.tomcat.Server;
import com.huangw1.tomcat.ServerStatus;
import com.huangw1.tomcat.config.ServerConfig;
import com.huangw1.tomcat.io.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by huangw1 on 2018/5/7.
 */

/**
 * 责任分离
 * Server 负责管理组件的生命周期
 */
public class SimpleServer implements Server {

    private static Logger logger = LoggerFactory.getLogger(SimpleServer.class);

    private volatile ServerStatus serverStatus = ServerStatus.STOP;

    private final List<Connector> connectorList;

    public SimpleServer(List<Connector> connectorList) {
        this.connectorList = connectorList;
    }

    @Override
    public void start() throws IOException {
        connectorList.stream().forEach(connector -> connector.start());
        serverStatus = ServerStatus.START;
        logger.info("Server start...");
    }

    @Override
    public void stop() {
        connectorList.stream().forEach(connector -> connector.stop());
        serverStatus = ServerStatus.STOP;
        logger.info("Server stop...");
    }

    @Override
    public ServerStatus getStatus() {
        return serverStatus;
    }

    @Override
    public List<Connector> getConnectorList() {
        return connectorList;
    }
}
