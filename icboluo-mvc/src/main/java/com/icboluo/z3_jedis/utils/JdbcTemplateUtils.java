package com.icboluo.z3_jedis.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class JdbcTemplateUtils {
    private static final DataSource ds = new ComboPooledDataSource();
    /**
     * 获取DataSources
     * @return
     */
    public static DataSource getDataSources() {
        return ds;
    }
}
