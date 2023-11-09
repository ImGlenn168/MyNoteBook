package com.java.mynotebook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBUtil {
    private static final String USER = "root";
    private static final String PWD = "123456";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/learnenglish?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
    private static Statement statement;
    private static Connection connection;
    private static DBUtil utils = null;
    // 预编译语句对象
    private static PreparedStatement preparedStatement;

    public DBUtil() {

    }

    // 不是线程安全的 如果有并发访问实例化的时候会出现线程安全的问题，解决办法加同步锁synchronized
    public synchronized static DBUtil getDBUtil() {
        System.out.println("1111111111");
        if (utils == null) {
            utils = new DBUtil();
            return utils;
        }
        return utils;
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PWD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Statement getStatement() {
        if (statement == null) {
            try {
                if (connection == null) {
                    connection = getConnection();
                }
                statement = connection.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    // 预编译语句对象
    public static PreparedStatement getPreparedStatement(String sql) {
        try {
            if (connection == null) {
                connection = getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

}
