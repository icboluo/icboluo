package com.icboluo.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2022-10-11 21:53
 */
@Component
public class LocalMybatis implements ApplicationContextAware {
    /**
     * mybatis 拦截器中 ApplicationContextAware 这个可以用 autowired代替，具体原因未知
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException exception
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SqlSessionFactory bean = applicationContext.getBean(SqlSessionFactory.class);
        TypeHandlerRegistry typeHandlerRegistry = bean.getConfiguration().getTypeHandlerRegistry();
        typeHandlerRegistry.register(LocalDate.class, LocalDateMyBatisType.class);
        typeHandlerRegistry.register(LocalDateTime.class, LocalDateTimeMyBatisType.class);
    }
}
