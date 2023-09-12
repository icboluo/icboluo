package com.icboluo.z2;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JDBCUtil {
    //初始化数据源
    private static DataSource dataSource = new ComboPooledDataSource("c3p0-config.xml");

    //获取JdbcTemplate对象
    public static JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
