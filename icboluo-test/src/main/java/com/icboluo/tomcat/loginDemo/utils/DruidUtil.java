package com.icboluo.tomcat.loginDemo.utils;

import javax.activation.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtil {
    private static DataSource dataSource;

    //数据库连接池应该只创建一次
    static {
        try {
            //创建properties对象
            Properties properties = new Properties();
            //IO流： 读取---物理地址（D）
            properties.load(new FileInputStream("druid.properties"));
            // ClassLoader classLoader = DruidUtil.class.getClassLoader();
            // InputStream inputStream = classLoader.getResourceAsStream("druiddb.properties");
            //  properties.load(inputStream);

            //创建数据库连接池
           // dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDateSourse() {
        return dataSource;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //创建连接对象
          //  connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void release(PreparedStatement ps, ResultSet rs, Connection connection) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ps = null;
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
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
