package com.icboluo.configuration;

import com.icboluo.util.BeanUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class CleanStartUp implements BeanPostProcessor {
    List<String> postConstructBeanNames = Arrays.asList("DeleteTask");

    /**
     * 清理后置处理器
     *
     * @param bean     the new bean instance
     * @param beanName the name of the bean
     * @return 如果return null，则不会执行 PostConstruct
     * @throws BeansException 异常
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (BeanUtil.containIgnoreCase(postConstructBeanNames, beanName)) {
            return null;
        } else {
            return bean;
        }
    }

    @Resource
    ApplicationContext applicationContext;

    @PostConstruct
    public void removeBeans() {
        List<String> beanNames = Arrays.asList("");
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        if (context == null) {
            return;
        }
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        for (String bean : beanNames) {
            bean = bean.substring(0, 1).toLowerCase(Locale.ENGLISH) + bean.substring(1);
            beanFactory.removeBeanDefinition(bean);
        }
    }

    // 利用切面修改方法，也可以先remove impl，再重新添加一个@service
    @Aspect
    @Component
    public class Int {
        @Pointcut(
                value = "execution(public * com.icboluo.controller.ResultController.integer(..))")
        public void cut() {

        }

        @Around("cut()")
        public Object aroundCut(ProceedingJoinPoint joinPoint) {
            return 123;
        }
    }
}
