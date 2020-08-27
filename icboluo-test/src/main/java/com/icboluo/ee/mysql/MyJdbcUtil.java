package com.icboluo.ee.mysql;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author icboluo
 * @date 2020-08-13 13:17
 */
public class MyJdbcUtil {
    public static final String UPDATE_SQL = "update user set password = ?";
    public static final String SELECT_SQL = "select * from user";
    public static final String PROPERTIES_ADDR = "src\\main\\java\\com/icboluo/test/ee/mysql//sql.properties";
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static String DRIVER_CLASS_NAME;

    //    只执行一次：静态代码块
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(PROPERTIES_ADDR));
            URL = properties.getProperty("URL");
            USERNAME = properties.getProperty("USERNAME");
            PASSWORD = properties.getProperty("PASSWORD");
            DRIVER_CLASS_NAME = properties.getProperty("DRIVER_CLASS_NAME");
            Class.forName(DRIVER_CLASS_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //ctrl alt+c
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
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

    public static void parseResultSet(ResultSet result) throws SQLException {
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            String pwd = result.getString("password");
            System.out.println(id + "===" + name + "===" + pwd);
        }
    }

    public static Connection getConnection2() {
        Connection connection = null;
        try {
            DataSource dataSource = new ComboPooledDataSource();
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
