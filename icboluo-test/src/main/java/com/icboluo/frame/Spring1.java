package com.icboluo.frame;
/*
Spring1:
   IoC（Inversion of Control 控制反转）： 将对象创建权利交给Spring工厂进行管理
   AOP（Aspect Oriented Programming 面向切面编程），基于动态代理的功能增强方式


Inversion of Control:去掉三层架构中的new
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

@Scope
@PostConstruct
@PreDestroy
相当于：<bean id="" class="" init-method="" destroy-method="" />

@Component《组件》 放在类上，把该类放进bean中，并设置其id（value）值
   @Controller @Service @Repository《仓库》
@Autowired注入：先直接找子类，找的多了按id找
   @Qualifier改id
   @Resource两者合一
   @Value注入url exa：
      @Value("${jdbc.url}")
       private String name;

@Configuration（AnnotationApplicationContext.......class）：想要替换applicationContext创建的类上注解
@ComponentScan:<context:component-scan base-package="cn.itcast"></context:component-scan>
@Bean:将方法的返回值作为一个bean,并且放入spring容器。 exa：配置jdbc（用@value跨类调用）
@PropertySource：加载properties中的资源
@Import：用于导入其他配置类

@RunWith（SpringJUnit4ClassRunner.class）：替换掉junit的运行器,换成一个可以初始化spring容器的运行器。
@ContextConfiguration（locations={"classpath：......"}）：加载配置类或者xml配置文件
 */
public class Spring1 {
}
