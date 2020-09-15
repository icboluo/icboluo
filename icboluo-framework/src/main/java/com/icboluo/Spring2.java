package com.icboluo;
/*
AOP：Aspect Oriented Programming《方面，以...为方向，》：
把重复代码抽取出来，使用的时候，利用动态代理在不修改源码的基础上增强
动态代理技术：要开始功能的增强了，就创建这个代理对象，对目标对象进行功能的增强。不可见
1、基于接口的JDK官方的动态代理（优先使用）要求：被代理类最少实现一个接口
2、基于子类的第三方的cglib的动态代理 要求：被代理类不能用final修饰的类

 <aop:config>配置AOP
 <aop:aspect id="logAdvice" ref="logger">配置切面，ref:引用通知类
 <aop:pointcut expression=" execution(* cn.itcast.service.impl.*.*(..))" id="pt1"/>用于配置切入点表达式，pointcut-ref="pt1"调用表达式。用于优化下面代码
 <aop:before method="printLog" pointcut="execution...配置前置通知，        method:配置通知方法（具体增强的方法）
                    pointcut:配置AspectJ表达式，即将通知增强到哪个方法
                    execution:使用AspectJ的切入点表达式，execution(修饰符 返回值类型 包名.类名.方法名(参数列表))
 <aop:after-returning>@AfterReturning
 <aop:after-throwing>@AfterThrowing
 <aop:after>@After（execution...）
 <aop:around>ProceedingJoinPoint需要传入该类参数
 @Pointcut切入点表达式：通过一个空方法单独配置切入点表达式

<!--开启注解AOP-->
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>@EnableAspectJAutoProxy放在总配置类中
@Aspect表示当前类是一个切面类（也可以称之为通知类）就是增强了什么的类


old：jdbctemplate中:setDataSource(dataSource);注入<bean...
new：JdbcDaoSupport，无法将dataSource注入到JdbcDaoSupport中注解用不了

配置事务管理器
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">...数据源
<!--配置事务策略
<tx:advice id="txAdvice" transaction-manager="transactionManager">
配置事务的属性
          <tx:attributes>
              <tx:method name="query*" read-only="false"/>
          </tx:attributes>...
配置AOP
<aop:config>
          配置切入点表达式-
          <aop:pointcut id="pt2" expression="execution(* com.task.service.impl.*.* ( .. ) )"></aop:pointcut>
          配置事务管理器应用到切入点
          <aop:advisor advice-ref="txAdvice" pointcut-ref="pt2">...

开启注解事务管理器
<tx:annotation-driven></tx:annotation-driven>
@Transactional 在类上、方法上、接口上使用事务注解


每一个servlet都要手动初始化spring容器，然后从容器中获取service层实现类，如果有很多个servlet的话就要初始化多次spring容器
Spring容器只有在客户端发送请求，请求到达服务端后才初始化spring容器，效率不高

解决思路：保证容器只有一个。并且在应用加载的时候启动，应用卸载的时候销毁

spring容器在应用加载的时候创建一次即可。spring提供了一个监听器ContextLoaderListener,位于spring-web-5.0.6.RELEASE.jar，
该监听器会初始化一个全局唯一的spring容器，监听ServletContext对象的创建时机
    <!--指定applicationContext.xml的位置-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <!--配置spring监听器-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
通过工具类获取spring容器
WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
 */
public class Spring2 {
}
