package com.icboluo.a_sql;

import com.alibaba.druid.pool.DruidDataSource;
import com.icboluo.mysql.MyDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;

/**
 * @author icboluo
 * @since 2020-08-13 15:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class z_DataSourceSimpleFactory {

    public static DataSource getDataSource() {
        return getDataSource("Druid");
    }

    public static DataSource getDataSource(String dataSourceName) {
        if ("Druid".equals(dataSourceName)) {
            DruidDataSource dataSource = new DruidDataSource();
            //        配置文件读取
/*        Properties properties = new Properties();
        properties.load(new FileInputStream("druiddb.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);*/
            dataSource.setDriverClassName("org.sqlite.JDBC");
            dataSource.setUrl("jdbc:sqlite::../../../document/sql/document.db");
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
