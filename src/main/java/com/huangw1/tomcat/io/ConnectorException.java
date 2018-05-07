package com.huangw1.tomcat.io;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class ConnectorException extends RuntimeException {

    public ConnectorException(String msg) {
        super(msg);
    }

    public ConnectorException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ConnectorException(Throwable throwable) {
        super(throwable);
    }

    public ConnectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
