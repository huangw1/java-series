package com.huangw1.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangw1 on 2018/5/6.
 */

/**
 * xml 配置等价
 * namespace / statementID / SQL 的存储、映射
 */
public class ExampleMapperXml {

    public static final String namespace = "mybatis";

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("queryExampleById", "select * from example where id = %s");
    }

    public static String getMethodSql(String method) {
        return map.get(method);
    }
}
