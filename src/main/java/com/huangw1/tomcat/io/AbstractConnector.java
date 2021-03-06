package com.huangw1.tomcat.io;

import com.huangw1.tomcat.LifeCycle;

/**
 * Created by huangw1 on 2018/5/7.
 */
public abstract class AbstractConnector<T> implements Connector {
    @Override
    public void start() {
        init();
        acceptConnect();
    }

    public abstract void init() throws ConnectorException;

    public abstract void acceptConnect() throws ConnectorException;

    public abstract void communicate(T t);
}
