package com.huangw1.mybatis;

import java.lang.reflect.Proxy;

/**
 * Created by huangw1 on 2018/5/6.
 */
public class DefaultMysqlSession implements MysqlSession {

    private Executor executor = new BaseExecutor();

    @Override
    public <T> T select(String sql) {
        return executor.query(sql);
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new MapperProxy(this));
    }
}
