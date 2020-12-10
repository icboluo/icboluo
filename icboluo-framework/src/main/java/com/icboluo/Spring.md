##  IoC（Inversion of Control 控制反转）：

将对象创建权利交给Spring工厂进行管理
 
##  AOP（Aspect Oriented Programming 面向切面编程）:

基于动态代理的功能增强方式

## 名词/注解

@Import：用于导入其他配置类

@RunWith（SpringJUnit4ClassRunner.class）：替换掉junit的运行器,换成一个可以初始化spring容器的运行器。

@Component《组件》 放在类上，把该类放进bean中，并设置其id（value）值

@Controller @Service @Repository《仓库》

@Autowired注入：先直接找子类，找的多了按id找

@Qualifier改id

@Resource两者合一
   
@Value注入url exa：
      
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

@Scope
@PostConstruct
@PreDestroy
相当于：<bean id="" class="" init-method="" destroy-method="" />


Joinpoint 连接点

PointCut 切点

Advice 增强

增强里面有各种通知

Target 目标对象

Weaving 织入

Proxy 代理类

Aspect 切面