package com.huangw1.tomcat.handler;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class HandlerException extends RuntimeException {

    public HandlerException(String msg) {
        super(msg);
    }

    public HandlerException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public HandlerException(Throwable throwable) {
        super(throwable);
    }

    public HandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
