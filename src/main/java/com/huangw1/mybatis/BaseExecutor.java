package com.huangw1.mybatis;

import java.sql.*;

/**
 * Created by huangw1 on 2018/5/6.
 */
public class BaseExecutor implements Executor {

    private static final String CONNECTION_URL = "";
    private static final String USER_NAME = "";
    private static final String PASSWORD = "";

    @Override
    public <T> T query(String sql) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Example example;
            if(resultSet.next()) {
                example = new Example();
                example.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
