package com.icboluo.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author icboluo
 * @since 2022-05-29 15:27
 */
@Slf4j
public class Demo {
    /**
     * 定义bean
     * 声明式定义bean的方式
     * <p>
     * bean 标签
     *
     * @Component 需要搭配@ComponentScan 来使用
     * @Bean xml 创建bean(bean标签
     */
    @Test
    public void xml() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student student = classPathXmlApplicationContext.getBean("student", Student.class);
        log.info(student.toString());
    }

    /**
     * 注解创建bean
     */
    @Test
    public void anno() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        annotationConfigApplicationContext.register(Student.class);
        Student stu = annotationConfigApplicationContext.getBean(Student.class);
        log.info(stu.toString());
    }

    /**
     * 编程式的bean定义方式
     * beanDefinition 创建bean
     */
    @Test
    public void definition() {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(Student.class);
    }

    @Test
    public void beanFactory() {
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        Student bean = beanFactory.getBean(Student.class);
    }

    /**
     * 实现此功能，会生成2个对象
     */
    public static class MyFactoryBean implements FactoryBean<MyFactoryBean> {
        @Override
        public MyFactoryBean getObject() throws Exception {
            return null;
        }

        @Override
        public Class<?> getObjectType() {
            return null;
        }
    }

}
