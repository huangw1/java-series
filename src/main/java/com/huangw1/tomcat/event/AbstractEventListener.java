package com.huangw1.tomcat.event;

/**
 * Created by huangw1 on 2018/5/7.
 */

import com.huangw1.tomcat.handler.EventHandler;

/**
 * 事件监听 / 事件处理
 * 职责分离
 *
 * protected 只能子类自己访问, 其他包下不能调用
 */
public abstract class AbstractEventListener<T> implements EventListener<T> {

    @Override
    public void onEvent(T t) {
        EventHandler<T> eventHandler = getEventHandler(t);
        eventHandler.handler(t);
    }

    protected abstract EventHandler<T> getEventHandler(T t);
}
