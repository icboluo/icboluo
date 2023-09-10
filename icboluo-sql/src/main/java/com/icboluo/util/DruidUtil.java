package com.icboluo.util;

import java.io.FileInputStream;
import java.util.Properties;

public class DruidUtil {
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
}
