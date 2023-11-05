package com.icboluo.feign;

import com.icboluo.util.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>去访问user服务
 * <p>在 @FeignClient 上仅仅指定服务名的时候，有时候不能调用成功，是因为服务没有注册到nacos上，feign无法通过服务名进行负载均衡，
 * 这个时候可以指定具体的http...地址即可，这样的话就脱离了nacos集成
 * <p> Feign 是一个声明式的HTTP客户端，它可以与Ribbon集成，通过注解的方式定义HTTP请求，自动实现负载均衡
 *
 * @author icboluo
 * @since 2020-08-27 19:21
 */
@FeignClient(value = "icboluo-user", url = "http://127.0.0.1:7010")
//@FeignClient(value = "icboluo-user")
//@FeignClient(value = "icboluo-user",configuration = MyLoadBalanced.class)
public interface UserFeign {
    /**
     * feign传参需要加上@RequestParam注解，否则对方服务无法识别参数
     *
     * @param id 主键
     * @return 用户名
     */
    @GetMapping("user/getUserNameById")
    SingleResponse<String> getUserNameById(@RequestParam("id") Integer id);
}
