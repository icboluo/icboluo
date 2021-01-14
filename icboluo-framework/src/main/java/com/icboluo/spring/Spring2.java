package com.icboluo.spring;
/*

    old：factory加载资源文件：private static ResourceBundle rb = ResourceBundle.getBundle("beans");
             new时由  factory.getbean  替代
    spring：applicationContext <bean>将serviceimpl 放在bean中
                  加载配置文件，获取serviceimpl
ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

1、默认无参构造实例化bean
2、静态工厂方法实例化bean：把静态方发返回的值放在bean中
class后面加factory-method

3、实例工厂方法实例化bean：非静态方法返回的值放在bean中
先和无参一样bean实例化，再用新的bean中factory-bean接收，factory-method写入方法

Dependency Injection（依赖注入）：构造方法注入、setter方法注入、p名称空间注入（基于set）等
构造函数注入属性值：涉及的标签 constructor-arg， set 方法注入 标签：Property《性质》
      index:指定参数在构造函数参数列表的索引位置
      name:指定参数在构造函数中的名称,指定给谁赋值
      value:它能赋的值是基本数据类型和 String 类型
      ref:它能赋的值是其他 bean 类型，也就是说，必须得是在配置文件中配置过的 bean

复杂注入 用<...<property>获取list...的name

<context:property-placeholder location 加载外部资源文件
<context《环境》 component-scan base-package扫描包下所有对应注解
<bean>属性：
   scope：指定对象的作用范围
      singleton :默认值，单例的
      prototype :多例的
   init-method：指定类中的初始化方法名称(生命周期相关)
   destroy-method：指定类中销毁方法名称(生命周期相关)


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
