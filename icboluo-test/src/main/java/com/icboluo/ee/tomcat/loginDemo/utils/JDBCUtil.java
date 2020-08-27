package com.icboluo.ee.tomcat.loginDemo.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class JDBCUtil {
    //初始化数据源
    private static DataSource dataSource = new ComboPooledDataSource();

    //返回数据源
    public static DataSource getDataSource() {
        return dataSource;
    }
}
