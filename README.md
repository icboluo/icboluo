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
#技术选型

##前后端分离+cloud

选择原因

1.不会写前端，不分离以前用mvc写起来恶心\
2.cloud扩展性高

##mvc+spring+mybatis

选择原因

只会用这些

## boot

选择原因

boot是spring一站式解决方案，要用spring，如果和boot搭配会

简化spring的使用难度，简化配置，提供快速启动器

##maven

选择原因

1.虽然看起来啰嗦但是看习惯了，而且好多官网都是maven写的，copy方便\
2.gradle写的不咋好

##nacos

功能：注册中心、配置中心

###注册中心替代品

cap(consistency强一致性 availability可用性（所有的请求都会收到响应） parti n tolerance 分区容错性)

eureka ap ,zookeeper、consul cp，nacos 可ap可cp

eureka：没人更新

eureka自我保护就是不会（立刻）清理死了的服务

eureka已经集成ribbon实现负载均衡算法，ribbon默认的负载均衡算法是轮训，可以修改成其他负载均衡算法或者自定义

ribbon是本地负载均衡，nginx是服务端的负载均衡（集中式的）

除了eureka用自己的@enable...service注解，其他的注册中心都用@EnableDiscoveryClient 

zookeeper：没用过

zookeeper上没有自我保护，一定时间内服务挂掉，会直清理

consul：go写的 

consul是分布式服务发现和配置管理系统


###配置中心替代品



## 发送请求

### 1.feign

使用方式1：

1.调用方要发送请求，需要提供依赖

2.调用方调用feign service

3.被调用者完成feign调用的接口

使用方式2：

1.被调用者提供client接口

2.调用方引入client依赖和feign依赖

3.调用方用引入的client依赖，或者用service将引入的依赖继承过来

第2种更好一点，被调用者提供被调用服务，第一种方式更简洁

注意：feign的service必须使用mvc注解

### 2. RestTemplate

使用方式：

1.注入到spring容器

2.从spring容器取出来在接口调用出使用



#项目介绍

##common

包含一些 common和util

##note

包含note和excel

TODO

分离2个模块

##software

TODO

包含java软件环境的安装 

##test

从hello world到framework

TODO

分离补充为多个模块

##user

提供对外访问的接口

##sql

提供sql的访问方式和sql的一些相关问题




