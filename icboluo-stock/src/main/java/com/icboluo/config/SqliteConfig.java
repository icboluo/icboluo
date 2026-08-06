package com.icboluo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteConfig;

import javax.sql.DataSource;

/**
 * SQLite 数据源配置
 * <p>仅设置 IMMEDIATE 事务模式（sqlite-jdbc Java API，无 YAML 对应项）。
 * 其余配置（url、driver）从 YAML 读取。
 * <p>IMMEDIATE 模式使事务开始即获取写锁，锁竞争时遵守 busy_timeout 等待重试，
 * 而 DEFERRED 模式下锁升级不遵守 busy_timeout，直接返回 SQLITE_BUSY。
 */
@Configuration
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "org.sqlite.JDBC")
public class SqliteConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        SQLiteConfig sqliteConfig = new SQLiteConfig();
        sqliteConfig.setTransactionMode(SQLiteConfig.TransactionMode.IMMEDIATE);
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setDriverClassName(driverClassName);
        ds.setMaximumPoolSize(5);
        ds.setMinimumIdle(1);
        ds.setConnectionInitSql("PRAGMA busy_timeout = 30000");
        ds.setDataSourceProperties(sqliteConfig.toProperties());
        return ds;
    }
}
