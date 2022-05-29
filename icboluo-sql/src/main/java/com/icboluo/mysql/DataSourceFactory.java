package com.icboluo.mysql;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * @author icboluo
 * @since 2020-08-13 15:37
 */
public class DataSourceFactory {

    public static DataSource getDataSource(int i) {
        if (i == 1) {
          return null;
        } else {

            DruidDataSource dataSource = new DruidDataSource();
            //        配置文件读取
/*        Properties properties = new Properties();
        properties.load(new FileInputStream("druiddb.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);*/
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/note");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
          return dataSource;
        }
    }
}
