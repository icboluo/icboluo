package com.icboluo.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * <p>自定义负载均衡需要2步
 * <p>1.自定义一个Bean ReactorLoadBalancer<ServiceInstance>
 * 这个Bean的定义不需要在雷伤标记@Componet，需要一个类中只有一个Bean
 * <p>2.在feign调用处标记出来使用的是那个 LoadBalanced
 *
 * @author icboluo
 * @see com.icboluo.feign.UserFeign
 * @since 2023-11-06 2:53
 */
public class MyLoadBalanced {
    @Bean
    public ReactorLoadBalancer<ServiceInstance> get(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        // AtomicInteger position 轮询负载均衡器里面使用的这个计数器，每次请求++，判断是哪个service使用&可以忽略符号位
        // AtomicInteger 增加到最大值的时候继续+1会变成负值，负值 %a 为负值
        return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
