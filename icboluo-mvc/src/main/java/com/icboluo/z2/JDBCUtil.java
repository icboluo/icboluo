package com.icboluo.z2;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JDBCUtil {
    //初始化数据源
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtil.class.getResourceAsStream("/druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取JdbcTemplate对象
    public static JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
