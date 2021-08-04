## IoC（Inversion of Control 控制反转）：

将对象创建权利交给Spring工厂进行管理

去掉三层架构中的new

set注入一次只能注入单个bean(可以用来给静态变量附初始化值)？

## AOP（Aspect Oriented Programming 面向切面编程）:

基于动态代理的功能增强方式

        把重复代码抽取出来，使用的时候，利用动态代理在不修改源码的基础上增强
        动态代理技术：要开始功能的增强了，就创建这个代理对象，对目标对象进行功能的增强。不可见
        动态代理实现方案：
        1、基于接口的JDK官方的动态代理（优先使用）要求：被代理类最少实现一个接口
        2、基于子类的第三方的cglib的动态代理 要求：被代理类不能用final修饰的类

## 名词/注解

@Import：用于导入其他配置类

@RunWith（SpringJUnit4ClassRunner.class）：替换掉junit的运行器,换成一个可以初始化spring容器的运行器。

@Component《组件》 放在类上，把该类放进bean中，并设置其id（value）值

@Controller @Service @Repository《仓库》

@Autowired注入：先直接找子类，找的多了按id找

@Qualifier改id

@Resource两者合一

@Value注入url exa：

> 注入必须加$符号，书写的时候需要注意

    属性注入，将制定配置文件中的属性注入到变量中
      
      @Value("${jdbc.url}")
       private String name;

@Configuration

    声明一个类作为配置类，代替xml文件
    相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，建议仍然通过@Configuration类作为项目的配置主类——可以使用@ImportResource注解加载xml配置文件

@PropertySource：加载properties中的资源

@Configuration（AnnotationApplicationContext.......class）：想要替换applicationContext创建的类上注解

@ComponentScan:<context:component-scan base-package="cn.itcast"></context:component-scan>

@Bean:

    将方法的返回值作为一个bean,并且放入spring容器。 exa：配置jdbc（用@value跨类调用）
    
    声明在方法上，将方法的返回值加入Bean容器，代替<bean>标签，spring会自动调用bean

@ContextConfiguration（locations={"classpath：......"}）：加载配置类或者xml配置文件

@Scope @PostConstruct @PreDestroy 相当于：<bean id="" class="" init-method="" destroy-method="" />

postConstruct 在创建对象后执行，整个系统中只执行一次，可以用来系统启动初始化某些属性

Joinpoint 连接点 连接点表示应用执行过程中能够插入切面的一个点， 这个点可以是方法的调用、异常的抛出。在 Spring AOP 中，连接点总是方法的调用

PointCut 切点 可以插入增强处理的连接点

Advice 通知 AOP 框架中的增强处理。通知描述了切面何时执行以及如何执行增强处理，有多种通知

增强里面有各种通知

Target 目标对象

Weaving 织入 将增强处理添加到目标对象中，并创建一个被增强的对象，这个过程就是织入，放进去的过程

Proxy 代理类

Aspect 切面 切面是通知和切点的结合，类上加这个注解，这个类就是切面

引入（Introduction）：引入允许我们向现有的类添加新的方法或者属性

## set注入

set注入只能注入单个参数

spring bean 默认单例

如果bean中有成员变量的时候（成员变量存储数据，不要去注入（di，使用new，因为成员变量会被更改
