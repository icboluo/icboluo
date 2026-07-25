package com.icboluo.common.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;


/**
 * 手动配置 SqlSessionFactory，解决 mybatis-plus-boot-starter 3.5.x
 * 与 Spring Boot 4 的 @AutoConfigureAfter 路径不兼容问题。
 * <p>
 * 注意：手动创建 SqlSessionFactory 会完全接管自动配置，因此必须把
 * {@code mybatis-plus.*} 的配置（mapper 映射文件位置、驼峰映射、全局配置等）
 * 重新设置进去，否则所有自定义 XML 语句都会报
 * {@code Invalid bound statement (not found)}，驼峰映射等配置也会失效。
 *
 * @author icboluo
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class MybatisPlusCompatConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, MybatisPlusProperties properties) throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        if (StringUtils.hasText(properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        }

        Resource[] mapperLocations = properties.resolveMapperLocations();
        if (mapperLocations == null || mapperLocations.length == 0) {
            // 配置的 mapper-locations 未解析到文件时的兜底，保证 XML 一定被加载
            mapperLocations = new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:mapper/**/*.xml");
        }
        if (mapperLocations != null && mapperLocations.length > 0) {
            factory.setMapperLocations(mapperLocations);
        }

        // MybatisPlusProperties.getConfiguration() 返回的是嵌套的 CoreConfiguration，
        // 需要转成 MybatisConfiguration 后再设置（不能直接当作 org.apache.ibatis.session.Configuration 使用）
        MybatisConfiguration configuration = new MybatisConfiguration();
        if (properties.getConfiguration() != null) {
            properties.getConfiguration().applyTo(configuration);
        }
        factory.setConfiguration(configuration);

        GlobalConfig globalConfig = properties.getGlobalConfig();
        if (globalConfig != null) {
            factory.setGlobalConfig(globalConfig);
        }

        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
