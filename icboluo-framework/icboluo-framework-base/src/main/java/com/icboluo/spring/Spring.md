## IoC（Inversion of Control 控制反转）：

IoC 把"对象创建"反转给框架，开发者从主动创建变为被动接收。

将对象创建权力交给 Spring 工厂管理，替代手动 new。

#### 传统方式的问题

每个 Servlet 都要手动初始化 Spring 容器，然后从容器中获取 Service 实现类。Servlet 多了会重复初始化，效率低下。

解决思路：保证容器只有一个。并且在应用加载的时候启动，应用卸载的时候销毁

为什么原本的main方法存活时间很短，现在的springboot项目存活时间很久：因为现在的main方法调用了死循环

main() → SpringApplication.run() → 启动内嵌服务器（如Tomcat）→ 阻塞等待请求（while True:socket = serverSocket.accept(); //
阻塞等待HTTP请求）

## 类

CommandLineRunner ApplicationRunner 实现这2个任意一个接口，可以实现项目启动之后执行代码（需要Component）

## 名词/注解

API: application platform interface

@Import：用于导入其他配置类

@RunWith（SpringJUnit4ClassRunner.class）：替换掉junit的运行器,换成一个可以初始化spring容器的运行器。

#### @Controller @Service @Repository (仓库) @RestController，

> @Controller 控制应用程序的流程和处理用户所发出的请求
> @RestController 将该注解使用在Controller类上，所有方法都默认是响应json格式的数据了

#### @RequestMapping @GetMapping @PostMapping @PutMapping @DeleteMapping

> @RequestMapping 提供路由信息，负责URL到Controller中的具体函数的映射

#### @RequestParam @PathVariable @ResponseBody

> RequestParam 并非完全没有作用，他比不加能适配的更多一些

@ResponseBody

    表示该方法的返回结果直接写入HTTP response body，
    是把Controller方法返回值转化为JSON，称为序列化
    是把接收到的JSON数据转化为Pojo对象，称为反序列化
    用于获取请求体内容。直接使用得到是key=value&key=value...结构的数据。 一般用于接收一个json数据，如果标记为false，说明整个对象都为null

#### @Autowired注入：先直接找子类，找的多了按id找

    Autowired作为方法注解的时候，需要注入参数；如果仅仅是在初始化的时候需要set默认值，我们可以使用PostConstruct
    @Qualifier改id
    该注解并不一定要搭配 autowired 使用，在设置多数据源的时候，bean注解也会让参数从bean容器中获取，仅仅 @Qualifier即可
    @jakarta.annotation.Resource = @Autowired+@Qualifier

#### @Value 属性注入,声明在属性（变量）上

> 注入必须加$符号，书写的时候需要注意；@Value注入url exa：

    // 属性注入，将制定配置文件中的属性注入到变量中
    @Value("${jdbc.url}")
    private String name;

@Value注解是不需要增加额外的依赖说明书写提示的，参考note服务pom注释

## set注入

    set注入只能注入单个参数
    spring bean 默认单例
    如果bean中有成员变量的时候（成员变量存储数据，不要去注入（di，使用new，因为成员变量会被更改

@Configuration

    声明一个类作为配置类，代替xml文件
    相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，建议仍然通过@Configuration类作为项目的配置主类——可以使用@ImportResource注解加载xml配置文件

@PropertySource：加载properties中的资源

@Configuration（AnnotationApplicationContext.......class）：想要替换applicationContext创建的类上注解

#### @ComponentScan

相当于 <context:component-scan base-package="com.icboluo"/>

#### @Bean @Component

@Component《组件》 放在类上，把该类放进bean中，并设置其id（value）值

    @Component由scan扫描到后自动装配到bean容器中（项目启动的时候调用）；bean（上的参数也是从容器中取的，不是随便写的（项目启动的时候调用）

@Bean:

    将方法的返回值作为一个bean,并且放入spring容器。 exa：配置jdbc（用@value跨类调用）
    
    声明在方法上，将方法的返回值加入Bean容器，代替<bean>标签，spring会自动调用bean

    bean和Component的区别：component作用于类，bean作用于方法；bean可以随便找个地方随便注入类（第三方工具，component不行

@ContextConfiguration(locations={"classpath：......"})：加载配置类或者xml配置文件

@Scope @PostConstruct @PreDestroy 相当于：<bean id="" class="" init-method="" destroy-method="" />

@PostConstruct 在创建对象后执行，整个系统中只执行一次，可以用来系统启动初始化某些属性,方法设置成私有的也是可以执行的

        虽然该注解来源于jdk，但是在jdk中是不会生效的，需要在Spring环境下

## AOP（Aspect Oriented Programming 面向切面编程）:

基于动态代理的功能增强方式

        把重复代码抽取出来，使用的时候，利用动态代理在不修改源码的基础上增强
        动态代理技术：要开始功能的增强了，就创建这个代理对象，对目标对象进行功能的增强。不可见
        动态代理实现方案：
        1、基于接口的JDK官方的动态代理（优先使用）要求：被代理类最少实现一个接口
        2、基于子类的第三方的cglib的动态代理 要求：被代理类不能用final修饰的类

#### 注意

    需要把aop加入spring bean，要不然aop不会起作用（类上需要加@Component注解
    直接调用Controller层方法和通过URL调用Controller接口，所经过的http切面是一致的，都无法避免
    受检异常exception在aop执行中，如果不处理，会造成抛出java.lang.reflect.UndeclaredThrowableException异常
    切面抛出的异常并非是因为受检异常什么的，jvm在处理动态代理的时候，如果出现顶层异常向下转型，会抛上述异常

#### AOP与代理

    aop存在于spring中，是对一个类做一个切面，通过spring获取这个类的时候（注入）其实就是调用到代理类；
    本类之间的调用，没有获取spring的额外的bean，自然就不会产生代理了；
    这块可以打断点调试，service层的aop，再controller注入的其实是cglib代理类

## AOP执行顺序

切面、拦截器、等环绕通知一定要记得调用原有方法

先执行的切面最后执行完，满足一般规律;先执行的切面最后执行完; 前置通知先执行，后置通知后执行（先进后出）
多切面：使用 @Order(值) 或实现 Ordered 接口设置顺序。 order值小的切面，@Before先执行

@around 最先开始执行，然后再执行before
@after 放到最后一行，最后面执行，相当于finally

1. 环绕通知（@Around）：环绕通知包裹了被切入的方法，在方法执行前后都可以执行额外的逻辑。环绕通知的执行顺序是先执行前置通知，然后执行被切入的方法，最后执行后置通知。
2. 前置通知（@Before）：前置通知在目标方法执行之前执行，可以在方法执行前执行一些准备工作。
3. 后置通知（@After）：后置通知在目标方法执行之后执行，无论目标方法是否抛出异常，后置通知都会执行。
4. 返回通知（@AfterReturning）：返回通知在目标方法执行并成功返回结果后执行，可以获取到目标方法的返回值。
5. 异常通知（@AfterThrowing）：异常通知在目标方法抛出异常时执行，可以捕获目标方法抛出的异常。

#### AOP名词

| 名词                  | 含义                                 |
|---------------------|------------------------------------|
| **Joinpoint** 连接点   | 可插入切面的执行点（Spring 中仅方法调用）           |
| **Pointcut** 切点     | 筛选连接点的**表达式**，决定哪些连接点需要增强          |
| **Advice** 通知       | 增强处理的逻辑（何时执行：Around/Before/After等） |
| **Aspect** 切面       | 通知 + 切点的组合（@Aspect 注解的类）           |
| **Target** 目标对象     | 被增强的原始对象                           |
| **Weaving** 织入      | 将通知织入目标对象，创建代理对象的过程                |
| **Proxy** 代理类       | 织入后生成的代理对象，封装了原始对象和通知逻辑            |
| **Introduction** 引入 | 向现有类动态添加方法或属性                      |

## SpringMvc:Model View Controller 模型视图控制器

Model:封装应用的状态，并实现应用的功能

View:提供界面来与用户进行人机交互

@SpringBootApplication 申明让spring boot自动给程序进行必要的配置@Configuration ，@EnableAutoConfiguration 和
@ComponentScan 三个配置。

ConfigurationProperties 声明当前类为属性读取类

Propertysource 指定外部属性文件

## PostHandler

post handler 在controller报异常的时候不会执行，需要用after handler

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

## SpringBoot

**@SpringBootApplication**：快速构建 Spring 项目，减少 XML 配置，开箱即用，关注业务而非配置。

**@PropertySource**：指定外部属性文件（通常配合 classpath 使用）。加载额外的外部配置文件

```java

@SpringBootApplication
@PropertySource(value = "classpath:db.properties")
public class App {
}
```

这个注解**已经使用不多**，多数配置直接在这里写 application.yml，约定俗成

替代方案: application.yml 直接导入（更常用）

```yaml
spring:
  config:
    import: classpath:db.properties
```

**属性注入原则（约定大于配置）**：

| 规则	 | 说明                                       |
|-----|------------------------------------------|
| 文件名 | application.properties 或 application.yml |
| 注解  | @ConfigurationProperties(prefix = "前缀")  |
| 映射  | POJO 字段名 = 配置键的最后一段                      |

```yaml
user:
  name: tom
  age: 18
```

```java

@ConfigurationProperties(prefix = "user")
public class User {
    private String name;  // 映射 user.name
    private Integer age;  // 映射 user.age
}
```

**激活方式**（二选一）：

1. `@Component`：属性类添加注解扫描进容器
2. `@EnableConfigurationProperties(属性类.class)`：在配置类上指定

```java
   // 方式1
@Component
@ConfigurationProperties(prefix = "user")
public class User {
}

// 方式2
@EnableConfigurationProperties(User.class)
@SpringBootApplication
public class App {
}
```

一句话: 约定大于配置：按命名规范放置配置文件，Spring Boot 自动绑定属性值。

## Spring Bean 实例化方式

1. 无参构造（默认）

```xml

<bean id="user" class="com.icboluo.User"/>
```

调用类的无参构造函数创建 bean。

2. 静态工厂方法

```xml

<bean id="user" class="com.icboluo.UserFactory" factory-method="createUser"/>
```

调用静态方法，返回值作为 bean。

```java
   public static User createUser() {
    return new User();
}
```

3. 实例工厂方法

```xml

<bean id="factory" class="com.icboluo.UserFactory"/>
<bean id="user" factory-bean="factory" factory-method="createUser"/>
```

先实例化工厂，再调用非静态方法，返回值作为 bean。

```java
   public User createUser() {
    return new User();
}
```

## 依赖注入（DI）

       Dependency Injection（依赖注入）：对象的依赖由 Spring 容器主动塞进来，而不是自己去找。
       Spring 替我们自动给对象的属性赋值，无需手动 set。

传统方式

```
UserService service = new UserServiceImpl();
service.setUserDao(new UserDaoImpl()); // 手动注入
```

依赖注入
// 只需要声明，Spring 自动赋值

```java

@Resource
private UserDao userDao;
```

#### 注入方式

1.构造注入

```xml

<bean id="user" class="com.icboluo.User">
    <constructor-arg name="id" value="1"/>
    <constructor-arg name="name" value="tom"/>
</bean>
```

constructor-arg 属性

| 属性    | 说明              |
|-------|-----------------|
| index | 参数在构造函数的索引位置    |
| name  | 参数名称            |
| value | 基本数据类型 / String |
| ref   | 引用其他 bean       |

2.set 注入

```xml

<bean id="user" class="com.icboluo.User">
    <property name="id" value="1"/>
    <property name="name" value="tom"/>
</bean>
```

p 名称空间注入（简化 set 注入）
p 名称空间就是 set 注入的简化写法，省掉 <property> 标签。
需在xml文件上面先引入：xmlns:p="http://www.springframework.org/schema/p"

```xml

<bean id="user" class="com.icboluo.User" p:id="1" p:name="tom"/>
```

3.字段注入（字段上面增加注解）

```xml
<!-- 加载外部属性文件 -->
<context:property-placeholder location="classpath:db.properties"/>
```

```xml
<!-- 扫描包（开启注解驱动）扫描包下所有对应注解 -->
<context:component-scan base-package="com.icboluo"/>
```

<bean>属性：

| 属性             | 说明                              |
|----------------|---------------------------------|
| scope          | 作用域：singleton 单例 / prototype 多例 |
| init-method    | 初始化方法                           |
| destroy-method | 销毁方法                            |

JdbcTemplate 注入方式

<!-- 旧方式：直接注入 -->

```xml

<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

<!-- 新方式：继承 JdbcDaoSupport -->

```xml

<bean id="userDao" class="com.icboluo.UserDaoImpl">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

JdbcDaoSupport 通过 setDataSource() 自动创建 JdbcTemplate。

## Spring事务配置

1. 配置事务管理器

```xml

<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

2. 配置事务通知

```xml

<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <tx:method name="query*" read-only="true"/>
        <tx:method name="*" read-only="false"/>
    </tx:attributes>
</tx:advice>
```

3. AOP 织入

```xml

<aop:config>
    <aop:pointcut id="pt2" expression="execution(* com.task.service.impl.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="pt2"/>
</aop:config>
```

## Spring容器初始化

利用 ContextLoaderListener(该监听器会初始化一个全局唯一的spring容器) 监听 ServletContext 创建，在应用启动时创建全局唯一容器。

```xml
<!--指定applicationContext.xml的位置-->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>
        <!--配置spring监听器-->
<listener>
<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

获取容器(通过工具类获取spring容器)：

```java
   WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
```

## Spring Boot 启动与请求处理流程：

1.在boot项目启动之后---bean初始化之后---执行aware回调的时候---会初始化applicationContext(
由ApplicationObjectSupport类承担)，会构建拦截器去List中
2.启动之后当 InitializingBean 子实现 AbstractHandlerMethodMapping.afterPropertiesSet 执行的时候，会调用
AbstractHandlerMethodMapping 会执行detectHandlerMethods 方法，Map.put(RequestMappingInfo(聚合请求路径)
，MappingRegistration(映射注册；聚合类名、方法名))
2在调用之后也会返回去调用1
3.DispatchServlet继承http Servlet，tomcat容器启动的时候会调用servlet的init函数，分发给子类dispatch称为onRefresh函数，onRefresh函数调用多个init函数;
mvc架构读取需要从上到下，上面的部分是接口部分功能，功能单一;例如aware通知接口;InitializingBean 接口

每一个http请求都要经过servlet接口的.service(req.res)方法
到http servlet类执行service方法，http servlet分发给framework抽象类
framework servlet调用intiContextHolders 再调用LocaleContextHolder.setLocaleContext即可设置request到thread中


启动时扫描注册映射，请求时通过 DispatchServlet 分发匹配 handlers。
启动阶段
启动类run()
↓
Bean 初始化
↓
Aware 回调 → ApplicationObjectSupport 初始化 ApplicationContext
↓
构建拦截器列表
↓
InitializingBean.afterPropertiesSet() → AbstractHandlerMethodMapping.detectHandlerMethods()
↓
Map<RequestMappingInfo, MappingRegistration> 注册映射


请求处理流程
HTTP 请求
↓
Servlet.service(req, res)
↓
HttpServlet.service() 分发
↓
FrameworkServlet.doDispatch()
↓
initContextHolders() → LocaleContextHolder.setLocaleContext(request)
↓
HandlerExecutionChain（执行链：Handler + 拦截器）
