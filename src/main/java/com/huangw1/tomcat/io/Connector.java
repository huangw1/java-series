package com.huangw1.tomcat.io;

import com.huangw1.tomcat.LifeCycle;

/**
 * Created by huangw1 on 2018/5/7.
 */
public interface Connector extends LifeCycle {

    int getPort();

    String getHost();
}
