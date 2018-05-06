package com.huangw1.mybatis;

/**
 * Created by huangw1 on 2018/5/6.
 */
public interface Executor {

    public <T> T query(String sql);
}
