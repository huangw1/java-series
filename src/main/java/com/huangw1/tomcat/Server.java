package com.huangw1.tomcat;

import com.huangw1.tomcat.io.Connector;

import java.io.IOException;
import java.util.List;

/**
 * Created by huangw1 on 2018/5/7.
 */
public interface Server {

    void start() throws IOException;

    void stop();

    ServerStatus getStatus();

    List<Connector> getConnectorList();
}
