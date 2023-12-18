package com.icboluo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author icboluo
 * @since 2023-08-22 1:08
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * <p>使用成员方法给静态变量赋值
     * <p>SpringBoot启动扫描到@Value注解修饰的set方法的时候，会调用这个方法，利用这个，可以给静态变量赋值
     * <p>Spring @Value注解不支持静态内容的注入，本质上是调用实例化set方法，就是说在字段上加相当于在任意实例化方法上加，由方法本身set进去
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException 异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> cla) {
        return applicationContext.getBean(cla);
    }
}
