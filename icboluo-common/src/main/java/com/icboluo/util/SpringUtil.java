package com.icboluo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 获取spring上下文
 * 如果注入的bean中含有可变的成员变量，因为单例的原因，成员变量被共享，如果不要共享，可以使用new 去使用
 *
 * @author icboluo
 * @date 2021-08-26 19:08
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    /**
     * 声明一个静态变量用于保存spring容器上下文
     */
    private static ApplicationContext context;

    @Override
    @Resource
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
