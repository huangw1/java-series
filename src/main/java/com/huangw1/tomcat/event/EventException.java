package com.huangw1.tomcat.event;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class EventException extends RuntimeException {

    public EventException(String msg) {
        super(msg);
    }

    public EventException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public EventException(Throwable throwable) {
        super(throwable);
    }

    public EventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
