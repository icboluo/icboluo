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

@Controller @Service @Repository《仓库》

@Autowired注入：先直接找子类，找的多了按id找

    autowired作为方法注解的时候，需要注入参数；如果仅仅是在初始化的时候需要set默认值，我们可以使用PostConstruct

@Qualifier改id

@Resource两者合一

@Value注入url exa：

@Value注解是不需要增加额外的依赖说明书写提示的，参考note服务pom注释

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

@Component《组件》 放在类上，把该类放进bean中，并设置其id（value）值

    @Component由scan扫描到后自动装配到bean容器中（项目启动的时候调用）；bean（上的参数也是从容器中取的，不是随便写的（项目启动的时候调用）

@Bean:

    将方法的返回值作为一个bean,并且放入spring容器。 exa：配置jdbc（用@value跨类调用）
    
    声明在方法上，将方法的返回值加入Bean容器，代替<bean>标签，spring会自动调用bean

    bean和Commponent的区别：component作用于类，bean作用于方法；bean可以随便找个地方随便注入类（第三方工具，component不行

@ContextConfiguration（locations={"classpath：......"}）：加载配置类或者xml配置文件

@Scope @PostConstruct @PreDestroy 相当于：<bean id="" class="" init-method="" destroy-method="" />

@PostConstruct 在创建对象后执行，整个系统中只执行一次，可以用来系统启动初始化某些属性,方法设置成私有的也是可以执行的

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

## 执行顺序

切面执行不知道什么顺序，但是可以设置执行顺序，用order即可

先执行的切面最后执行完，满足一般规律

## aop

需要把aop加入spring bean，要不然aop不会起作用（类上需要加@Component注解

受检异常exception在aop执行中，如果不处理，会造成抛出java.lang.reflect.UndeclaredThrowableException异常

切面抛出的异常并非是因为受检异常什么的，jvm在处理动态代理的时候，如果出现顶层异常向下转型，会抛上述异常

## Springmvc:Model View Controller 模型视图控制器

## 名词/注解

Model:封装应用的状态，并实现应用的功能

View:提供界面来与用户进行人机交互

@Controller:控制应用程序的流程和处理用户所发出的请求

@ResponseBody 表示该方法的返回结果直接写入HTTP response body，

@ResponseBody 是把Controller方法返回值转化为JSON，称为序列化

@RestController，将该注解使用在Controller类上，所有方法都默认是响应json格式的数据了

@RequestBody 是把接收到的JSON数据转化为Pojo对象，称为反序列化

@RequestBody用于获取请求体内容。直接使用得到是key=value&key=value...结构的数据。 一般用于接收一个json数据

如果标记为false，说明整个对象都为null

RequestMapping 提供路由信息，负责URL到Controller中的具体函数的映射

@RequestParam@PathVariable@GetMapping@PostMapping@PutMapping@DeleteMapping

@SpringBootApplication 申明让spring boot自动给程序进行必要的配置@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。

ConfigurationProperties 声明当前类为属性读取类

Propertysource 指定外部属性文件

@Value 属性注入,声明在属性（变量）上

## PostHandler

post handler 在controller报异常的时候不会执行，需要用after handler

## RequestParam

requestParam并非完全没有作用，他比不加能适配的更多一些

```java
public class SpringIoc {
    /**
     * spring 的注入方式
     */
    @Resource
    private ArchivesVO archivesVO;

    /**
     * 相当于
     */
    private ArchivesVO archives = getArchivesVO();

    private singleton getArchivesVO() {
        // 单例注入 map：ioc容器
        if (map.get(ArchivesVO) == null) {
            map.put(new ArchivesVO());
        }
        return map.get(ArchivesVO);
    }

    private prototype getArchivesVO() {
        // 多例注入
        return new ArchivesVO();
    }
}
```

```java
public class SpringBoot {

    private static SpringIoc sp;

    /**
     * set方法注入
     *
     * @param sp 需要注入的静态变量
     */
    @Resource
    private void setSp(SpringIoc sp) {
        SpringBoot.sp = sp;
    }
}
```

```java
public class StaticPri {

    private static ArchivesVO archivesVO;

    @Resource
    private void setArchivesVO(ArchivesVO archivesVO) {
        StaticPri.archivesVO = archivesVO;
    }

    /**
     * 静态内容调成员内容
     */
    private static void staticComm() {
        // 但是new 出来的对象在Spring中，如果有DI则无法使用，会是null值
        StaticPri staticPri = new StaticPri();
        staticPri.setArchivesVO(null);
    }
}
```

## 源码


