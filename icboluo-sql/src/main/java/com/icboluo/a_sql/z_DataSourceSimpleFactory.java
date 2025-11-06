package com.icboluo.a_sql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.icboluo.mysql.MyDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author icboluo
 * @since 2020-08-13 15:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class z_DataSourceSimpleFactory {

    public static DataSource getDataSource() {
        return getDataSource("Druid");
    }

    @SneakyThrows
    public static DataSource getDataSource(String dataSourceName) {
        if ("Druid".equals(dataSourceName)) {
            //        配置文件读取
            InputStream is = z_DataSourceSimpleFactory.class.getResourceAsStream("/application-simple.yml");
            Properties properties = new Properties();
            properties.load(is);
            DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            dataSource.setValidationQuery("select 1");
            return dataSource;
        } else if ("Hikari".equals(dataSourceName)) {
            HikariDataSource dataSource = new HikariDataSource();
            //        配置文件读取
//            Properties properties = new Properties();
//            properties.load(new FileInputStream("druiddb.properties"));
//            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/note");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        }
        return new MyDataSource();
    }
}
