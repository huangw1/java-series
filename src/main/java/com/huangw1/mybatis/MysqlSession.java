package com.huangw1.mybatis;

/**
 * Created by huangw1 on 2018/5/6.
 */

/**
 * select / insert / update / delete
 */
public interface MysqlSession {

    public <T> T select(String sql);

    public <T> T getMapper(Class<T> clazz);
}
