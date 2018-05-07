package com.huangw1.tomcat.event;

/**
 * Created by huangw1 on 2018/5/7.
 */
public interface EventListener<T> {

    void onEvent(T event);
}
