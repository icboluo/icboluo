package com.icboluo.feign;

import com.icboluo.util.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 去访问user服务
 *
 * @author icboluo
 * @since 2020-08-27 19:21
 */
@FeignClient(value = "icboluo-user",url = "http://127.0.0.1:7010")
public interface UserFeign {
    /**
     * feign传参需要加上@RequestParam注解，否则对方服务无法识别参数
     *
     * @param id 主键
     * @return 用户名
     */
    @GetMapping("/user/getUserNameById")
    SingleResponse<String> getUserNameById(@RequestParam("id") Integer id);
}
