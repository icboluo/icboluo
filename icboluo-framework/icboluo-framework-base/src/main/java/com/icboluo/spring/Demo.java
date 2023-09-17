package com.icboluo.spring;

import com.icboluo.spring.bean.*;
import com.icboluo.spring.util.LifecycleBean;
import com.icboluo.spring.util.ScopeBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * @author icboluo
 * @since 2022-05-29 15:27
 */
@Slf4j
public class Demo {
    /**
     * <p>声明式定义（创建）bean的方式
     * <p>此块使用的是bean 标签创建bean举例
     * <p>Component注解 需要搭配@ComponentScan 来使用
     * <p>Bean注解创建bean
     * <p>xml 创建bean(bean标签创建bean)
     */
    @Test
    public void xml() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        // 直接获取bean
        Student student = ac.getBean("student", Student.class);
        log.info(student.toString());
        // 构造方法获取bean
        Student constructorStudent = ac.getBean("student2", Student.class);
        log.info("constructor method create bean: " + constructorStudent);
        // set方法获取bean
        Student3 setStudent = ac.getBean("student3", Student3.class);
        log.info("set method create bean: " + setStudent);
        // 创建复杂bean
        Student5 complexStudent = ac.getBean("student5", Student5.class);
        log.info("create complex bean: " + complexStudent);
        // 方法直接创建bean
        Student staticMethodCreateStudent = ac.getBean("methodCreateStudent1", Student.class);
        log.info("static method direct create bean " + staticMethodCreateStudent);

        Student instanceMethodCreateStudent = ac.getBean("methodCreateStudent2", Student.class);
        log.info("instance method indirect create bean " + instanceMethodCreateStudent);
        // BeanFactory 创建bean
        Student0 bfStudent = (Student0) com.icboluo.spring.util.BeanFactory.getBean("student0");
        log.info("BeanFactory create bean: " + bfStudent);

        // bean的作用域
        ScopeBean scopeBean1 = (ScopeBean) ac.getBean("scopeBean");
        log.info("scopeBean1 = " + scopeBean1);
        ScopeBean scopeBean2 = (ScopeBean) ac.getBean("scopeBean");
        log.info("scopeBean2 = " + scopeBean2);

        // bean的生命周期
        LifecycleBean lifecycleBean = (LifecycleBean) ac.getBean("lifecycleBean");
        log.info("lifecycleBean = " + lifecycleBean);
        ((ClassPathXmlApplicationContext) ac).close();
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

    @Test
    public void applicationContext() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Resource resource = applicationContext.getResource("spring.xml");
        System.out.println("resource = " + resource);

        System.out.println(applicationContext.getEnvironment());
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
