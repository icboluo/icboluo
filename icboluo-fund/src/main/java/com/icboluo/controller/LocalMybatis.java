package com.icboluo.controller;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author icboluo
 * @since 2022-10-11 21:53
 */
@Component
public class LocalMybatis implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SqlSessionFactory bean = applicationContext.getBean(SqlSessionFactory.class);
        TypeHandlerRegistry typeHandlerRegistry = bean.getConfiguration().getTypeHandlerRegistry();
        typeHandlerRegistry.register(LocalDate.class, MyBatisType.class);
    }
}
