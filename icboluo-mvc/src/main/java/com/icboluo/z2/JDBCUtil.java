package com.icboluo.z2;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

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
        return new JdbcTemplate(dataSource);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
