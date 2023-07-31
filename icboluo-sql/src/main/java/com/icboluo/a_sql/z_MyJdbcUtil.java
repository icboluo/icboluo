package com.icboluo.a_sql;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author icboluo
 * @since 2020-08-13 13:17
 */
public class z_MyJdbcUtil {
    public static final String UPDATE_SQL = "update user set password = ?";
    public static final String SELECT_SQL = "select * from user";
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static String DRIVER_CLASS_NAME;

    //    只执行一次：静态代码块
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/mybatis.xml"));
            URL = properties.getProperty("URL");
            USERNAME = properties.getProperty("USERNAME");
            PASSWORD = properties.getProperty("PASSWORD");
            DRIVER_CLASS_NAME = properties.getProperty("driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if (statement != null) {

            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
}
