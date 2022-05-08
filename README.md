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

* JDK17
* 采用简单的Spring全家桶
* Mysql
* 装这些就可以啦,项目可以启动个七七八八了
* 可以使用任意一个[application.simple](icboluo-note/src/main/resources/application-simple.yml)
  配置文件，用这个配置文件，项目启动不需要任何三方服务的支持（但是功能也会受影响

### 项目更新了LeetCode中的一些算法解法

在[algorithm](icboluo-algorithm/src/main/java/com/icboluo/leetcode)模块中可以找到

解法有好多是来源于Discuss中的思路

### common服务提供公共工具

- 有[过滤器](icboluo-common/icboluo-supper/src/main/java/com/icboluo/filter/HttpFilter.java)
  、[拦截器](icboluo-common/icboluo-supper/src/main/java/com/icboluo/interceptor/WebContextInterceptor.java)
  、[日志切面](icboluo-common/icboluo-supper/src/main/java/com/icboluo/aop/HttpAspect.java)
- [统一返回值](icboluo-common/icboluo-supper/src/main/java/com/icboluo/common/ResponseResultHandler.java)
  、[全局异常处理](icboluo-common/icboluo-supper/src/main/java/com/icboluo/common/GlobalControllerExceptionHandler.java)
- [Redis工具类](icboluo-common/icboluo-mapper/src/main/java/com/icboluo/common/redis/RedisLogAspect.java)、 还有一些常量枚举啥的

### fund服务是基金服务

### game服务是一个小游戏

### note服务是笔记本功能，包含Excel-Mysql之间的转换

### se服务提供了Java SE 部分的内容

[Base基础](icboluo-se/src/main/java/com/icboluo/base/clazz.md)\
[Stream](icboluo-se/src/main/java/com/icboluo/lambda)\
[Exception](icboluo-se/src/main/java/com/icboluo/exception)\
[DesignPattern](icboluo-se/src/main/java/com/icboluo/designpattern)\
[File](icboluo-se/src/main/java/com/icboluo/file)\
[Reflect](icboluo-se/src/main/java/com/icboluo/reflect)\
[JDK](icboluo-se/src/main/java/com/icboluo/jdk)\
[compare](icboluo-se/src/main/java/com/icboluo/compare)

### sql服务提供mysql、redis、mq、mybatis等工具使用

[Redis](icboluo-sql/src/main/java/com/icboluo/Redis.md)简述\
[Mysql](icboluo-sql/src/main/java/com/icboluo/Mysql.md)简述\
[Mybatis](icboluo-sql/src/main/java/com/icboluo/Mybatis.md) Learn\
[Mq](icboluo-sql/src/main/java/com/icboluo/mq) Learn

### 其他服务暂时没有实际意义上的功能

[查看详情](document/README.md)

