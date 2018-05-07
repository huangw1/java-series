package com.huangw1.tomcat.handler;

/**
 * Created by huangw1 on 2018/5/7.
 */
public interface EventHandler<T> {

    void handler(T t);
}
