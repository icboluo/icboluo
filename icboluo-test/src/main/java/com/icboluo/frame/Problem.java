package com.icboluo.frame;

public class Problem {
        /*
Configuration
Bean
Value
Propertysource
ConfigurationProperties
SpringBootApplication
ResponseBody
Controller
RestController
RequestMapping
Configuration
     */
    /*
    声明一个类作为配置类，代替xml文件
    声明在方法上，将方法的返回值加入Bean容器，代替<bean>标签
    属性注入,声明在属性（变量）上
    指定外部属性文件
    声明当前类为属性读取类
    申明让spring boot自动给程序进行必要的配置@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。
      表示该方法的返回结果直接写入HTTP response body，
用于定义控制器类，在spring项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（service层）
用于标注控制层组件(如struts中的action)，@ResponseBody和@Controller的合集。
提供路由信息，负责URL到Controller中的具体函数的映射。
相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，建议仍然通过@Configuration类作为项目的配置主类——可以使用@ImportResource注解加载xml配置文件
     */
}
