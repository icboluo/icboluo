package com.icboluo;
/*
Spring1:



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




@Configuration（AnnotationApplicationContext.......class）：想要替换applicationContext创建的类上注解
@ComponentScan:<context:component-scan base-package="cn.itcast"></context:component-scan>
@Bean:将方法的返回值作为一个bean,并且放入spring容器。 exa：配置jdbc（用@value跨类调用）
@PropertySource：加载properties中的资源



@ContextConfiguration（locations={"classpath：......"}）：加载配置类或者xml配置文件
 */
public class Spring1 {
}
