package com.icboluo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>RestTemplate 是支持负载均衡的，只要在 RestTemplate 上指定 @LoadBalanced(底层使用Ribbon）,在发送请求的时候使用服务名发送即可
 * <p>软件的负载均衡一般有2种方法：
 * <p>1.nginx、haproxy分布式服务器网关的负载均衡
 * <p>2.ribbon&feign结合nacos本地消费者客户端进程内的负载均衡（不用先去访问nginx这类的服务器网关），用于 SpringCloud
 * <p>----------------------------------------------------------------
 * <p>Ribbon是一个客户端的负载均衡，与配置中心集成
 * <p>客户端负载均衡指的是在请求发送方就知道了要发送给那个服务，例如note服务发送之前就算出来需要发送给那个user服务，表现是负载均衡是在客户端
 * note服务上完成的（这里的客户端指的是发送请求方，不是浏览器上的客户端
 * <p>客户端负载均衡的实现原理是通过注册中心，将可用的服务列表拉去到本地（客户端），再通过客户端负载均衡器（设置的负载均衡策略）获取到某个服务的
 * 具体ip和端口；客户端的负载均衡要求服务都注册到注册中心上去，否则注册中心无法提供服务列表
 * <p>服务端负载均衡名字具有误导作用，如果请求已经到具体的服务器 ，再负载也没有用了，所以服务端负载均衡指的是中介（网关）的负载均衡，客户端发送
 * 请求去网关，网关转发到具体的服务，转发的策略由网关的路由等策略完成
 *
 * @author icboluo
 * @since 2020-08-27 21:08
 */
@Configuration
public class RestTemplateConfig {
    /**
     * <p>产生一个bean对象，将这个对象交个spring管理
     * <p>@LoadBalanced 在客户端使用 RestTemplate 请求服务端时，开启负载均衡（Ribbon）
     *
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
