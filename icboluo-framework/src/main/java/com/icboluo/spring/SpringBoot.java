package com.icboluo.spring;
/*
  boot其最主要作用就是帮我们快速的构建庞大的spring项目，并且尽可能的
减少一切xml配置，做到开箱即用，迅速上手，让我们关注与业务而非配置。
pom中管理jdk版本：
<properties>
    <java.version>1.8</java.version>
</properties>
- @Configuration：声明一个类作为配置类，代替xml文件
- @Value：属性注入，将制定配置文件中的属性注入到变量中
- @PropertySource：(性质来源)指定外部属性文件，一般用classpath指定路劲
spring的属性注入：SpringBoot强调的是约定大于配置，因此遵循约定，我们就能节省很多配置：
- 首先，属性文件的名称有变化，文件名必须是：application.properties
- 其次，要注入的属性的变量名要和配置文件中的属性名的最后一部分保持一致
- 最后，要在类上声明这些属性在属性文件中的共同的前缀，并提供getter和setter方法
属性读取类激活的2中方式:
在属性读取类（jdbc.properties）中添加@Component（组成）注解
在配置类上使用@EnableConfigurationProperties(JdbcProperties.class)
 */
public class SpringBoot {
}
