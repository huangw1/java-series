package com.huangw1.tomcat;

import java.io.IOException;

/**
 * Created by huangw1 on 2018/5/7.
 */
public interface Server {

    void start() throws IOException;

    void stop();

    ServerStatus getStatus();

    int getPort();
}
