package com.icboluo.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author icboluo
 * @since 2023-08-22 1:08
 */
@Component
public class SpringUtil {

    @Autowired
    private BeanFactory beanFactory;


    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    public <T> T getBean(Class<T> cla) {
        return beanFactory.getBean(cla);
    }
}
