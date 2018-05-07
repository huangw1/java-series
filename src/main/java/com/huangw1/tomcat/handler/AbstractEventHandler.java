package com.huangw1.tomcat.handler;

/**
 * Created by huangw1 on 2018/5/7.
 */

/**
 * protected 只能子类自己访问, 其他包下不能调用
 */
public abstract class AbstractEventHandler<T> implements EventHandler<T> {

    @Override
    public void handler(T t) {
        beforeHandler(t);
        doHandler(t);
        afterHandler(t);
    }

    protected void beforeHandler(T t) {

    }

    protected abstract void doHandler(T t);

    protected void afterHandler(T t) {

    }
}
