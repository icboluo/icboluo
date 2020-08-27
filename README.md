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
##技术选型

###前后端分离+cloud

选择原因

1.不会写前端，以前用mvc写起来恶心\
2.cloud扩展性高

###mvc+spring+mybatis

选择原因

只会用这些

###maven

选择原因

1.虽然看起来啰嗦但是看习惯了，而且好多官网都是maven写的，copy方便\
2.gradle写的不咋好

###nacos

功能：注册中心、配置中心

替代品

eureka：没人更新

zookeeper：没用过

consul：go写的

### 发送请求
#### 1.feign

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

#### 2. RestTemplate

使用方式：

1.注入spring容器

2.从spring容器取出来在接口调用出使用



##项目介绍

###common

包含一些 common和util

###note

包含note和excel

TODO

分离2个模块

###software

TODO

包含java软件环境的安装 

###test

从hello world到framework

TODO

分离补充为多个模块

###user

提供对外访问的接口




