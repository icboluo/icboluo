### Hi there 👋

<!--
**icboluo/icboluo** is a ✨ _special_ ✨ repository because its `README.md` (this file) appears on your GitHub profile.

Here are some ideas to get you started:

- 🔭 I’m currently working on ...
- 🌱 I’m currently learning ...
- 👯 I’m looking to collaborate on ...
- 🤔 I’m looking for help with ...
- 💬 Ask me about ...
- 📫 How to reach me: ...
- 😄 Pronouns: ...
- ⚡ Fun fact: ...
-->

### 项目是学习Java的一些笔记

## 项目主体

* JDK21
* 采用简单的Spring全家桶
* Mysql/Sqlite3/Redis
* 装这些就可以啦,项目可以启动个七七八八了
* 可以使用任意一个[application.simple](icboluo-note/src/main/resources/application-simple.yml)
  配置文件，用这个配置文件，项目启动不需要任何三方服务的支持（但是功能也会受影响

## document

[sql文件](document/sql)\
[IDEA settings](document/settings.zip)

### 项目更新了LeetCode中的一些算法解法

[algorithm](icboluo-algorithm/src/main/java/com/icboluo)

[算法](icboluo-algorithm/src/main/java/com/icboluo/algorithm)\
[数据结构](icboluo-algorithm/src/main/java/com/icboluo/datastructure)\
[leetcode](icboluo-algorithm/src/main/java/com/icboluo/leetcode) 题目，2600以后题目不再维护\

解法有好多是来源于Discuss中的思路

### common服务提供公共工具

- 有[过滤器](icboluo-web/src/main/java/com/icboluo/filter/HttpFilter.java)
  、[拦截器](icboluo-common/icboluo-supper/src/main/java/com/icboluo/interceptor/WebInterceptor.java)
  、[日志切面](icboluo-common/icboluo-supper/src/main/java/com/icboluo/aop/HttpAspect.java)
- [统一返回值](icboluo-common/icboluo-supper/src/main/java/com/icboluo/common/ResponseResultHandler.java)
  、[全局异常处理](icboluo-common/icboluo-supper/src/main/java/com/icboluo/common/GlobalControllerExceptionHandler.java)
- [Redis工具类](icboluo-common/icboluo-mapper/src/main/java/com/icboluo/common/redis/RedisLogAspect.java)、
  [BaseMapper层](icboluo-common/icboluo-mapper/src/main/java/com/icboluo/common/MyBaseMapper.java)
- 还有一些常量枚举啥的

### framework

[MarkDown](icboluo-framework/icboluo-framework-base/src/main/java/com/icboluo/framework/MarkDown.md)语法\
[Spring](icboluo-framework/icboluo-framework-base/src/main/java/com/icboluo/spring) Learn\
[Thread](icboluo-framework/icboluo-framework-base/src/main/java/com/icboluo/thread)\
[Redis](icboluo-framework/icboluo-framework-base/src/main/java/com/icboluo/framework/Redis.md)简述\
[Mybatis](icboluo-framework/icboluo-framework-base/src/main/java/com/icboluo/framework/Mybatis.md) Learn\
[反射](icboluo-framework/icboluo-framework-base/src/main/java/com/icboluo/framework/ReflectTest.java)\
[正则](icboluo-framework/icboluo-framework-base/src/main/java/com/icboluo/framework/RegexTest.java)\

### fund服务是基金服务

[Fund](icboluo-fund/src/main/java/com/icboluo)

### game服务是一个小游戏

[Game](icboluo-game/src/main/java/com/icboluo)\
[国际化](icboluo-game/src/main/resources/i18n)\
[飞机大战1](icboluo-game/src/main/java/com/icboluo/fjdz)\
[飞机大战2](icboluo-game/src/main/java/com/icboluo/plane2)

### gateway 服务内嵌Redis，服务启动，Redis服务自动启动

### mvc 服务包含 tomcat servlet mvc

### note服务是笔记本功能，包含Excel-Mysql之间的转换

[Note](icboluo-note/src/main/java/com/icboluo)\
[EasyExcel](icboluo-note/src/main/java/com/icboluo/controller/ExcelController.java) 使用\
[笔记本](icboluo-note/src/main/java/com/icboluo/controller/TimeNoteController.java)\
[Feign](icboluo-note/src/main/java/com/icboluo/feign/UserFeign.java) 的简单使用\
[RestTemplate](icboluo-note/src/main/java/com/icboluo/controller/UserController.java) 的简单使用\
[Feign](icboluo-note/src/main/java/com/icboluo/feign/UserFeign.java) 的简单使用

### se服务提供了Java SE 部分的内容

[注解](icboluo-se/src/main/java/com/icboluo/annotation)\
[Base基础](icboluo-se/src/main/java/com/icboluo/base/clazz.md)\
[SpringBoot](icboluo-se/src/main/java/com/icboluo/base/spring.md)\
[比较器](icboluo-se/src/main/java/com/icboluo/compare)\
[设计模式](icboluo-se/src/main/java/com/icboluo/designpattern)\
[异常处理](icboluo-se/src/main/java/com/icboluo/exception)\
[IO流](icboluo-se/src/main/java/com/icboluo/file)\
[JDK源码](icboluo-se/src/main/java/com/icboluo/jdk)\
[HashMap1.7源码](icboluo-se/src/main/java/com/icboluo/jdk/hashmap/HashMap7.java)\
[内部类](icboluo-se/src/main/java/com/icboluo/nonameclass)\
[Stream](icboluo-se/src/main/java/com/icboluo/stream)\
[线程](icboluo-se/src/main/java/com/icboluo/thread)\
[File](icboluo-se/src/main/java/com/icboluo/file)

### sql服务提供mysql、redis、mq、mybatis等工具使用

[Mq](icboluo-sql/src/main/java/com/icboluo/mq) Learn

### user服务提供接口，用来给其他服务调用，无实质内容

[User](icboluo-user/src/main/java/com/icboluo)

### web服务用作spring调试调用

[Web](icboluo-web/src/main/java/com/icboluo)\
[Async](icboluo-web/src/main/java/com/icboluo/controller/AsyncController.java) 异步注解

[查看详情](document/README.md)

