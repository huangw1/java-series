package com.huangw1.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by huangw1 on 2018/5/6.
 */
public class MapperProxy implements InvocationHandler {

    private MysqlSession mysqlSession;

    public MapperProxy(MysqlSession mysqlSession) {
        this.mysqlSession = mysqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperClass = method.getDeclaringClass().getName();
        if(ExampleMapperXml.namespace.equals(mapperClass)) {
            String methodName = method.getName();
            String sql = ExampleMapperXml.getMethodSql(methodName);
            String formatSql = String.format(sql, args[0]);
            return mysqlSession.select(formatSql);
        }
        return null;
    }
}
