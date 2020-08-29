package com.icboluo.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author icboluo
 * @date 2020-08-13 15:37
 */
public class MyDataSourceFactory {

    public static DataSource getDataSource(int i) {
        DataSource dataSource;
        if (i == 1) {
            //创建核心类对象 ComboPooledDataSource就是DataSource的一个实现类
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            //        自动找xml文件并解析
/*        DataSource cpds = new ComboPooledDataSource();
        Connection connection = cpds.getConnection();*/
            try {
                cpds.setDriverClass("com.mysql.jdbc.Driver");
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            cpds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/note");
            cpds.setUser("root");
            cpds.setPassword("root");
            dataSource = cpds;
        } else {

            DruidDataSource dataSource1 = new DruidDataSource();
            //        配置文件读取
/*        Properties properties = new Properties();
        properties.load(new FileInputStream("druiddb.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);*/
            dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/note");
            dataSource1.setUsername("root");
            dataSource1.setPassword("root");
            dataSource = dataSource1;
        }
        return dataSource;
    }
}
