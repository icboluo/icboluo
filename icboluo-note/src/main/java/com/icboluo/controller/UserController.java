package com.icboluo.controller;

import com.icboluo.feign.UserFeign;
import com.icboluo.service.UserService;
import com.icboluo.util.response.R;
import com.icboluo.util.response.SingleResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author icboluo
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserFeign userFeign;

    @Resource
    private RestTemplate restTemplate;

    private static final String USER_SERVICE = "http://localhost:7010";

    @GetMapping("login")
    public boolean login(@RequestParam String name, @RequestParam String password) {
        return userService.validate(name, password);
    }

    @GetMapping("updatePassword")
    public boolean updatePassword(@RequestParam Integer id, @RequestParam String password) {
        return userService.updatePassword(id, password);
    }

    @GetMapping("feign/getUserNameById")
    public String feignGetUserNameById(@RequestParam Integer id) {
        SingleResponse<String> response = userFeign.getUserNameById(id);
        return response.getData();
    }

    @GetMapping("restTemplate/getUserNameById")
    public Object restTemplateGetUserNameById(@RequestParam Integer id) {
/*        这个是放在请求体中传输的
        String obj = restTemplate.postForObject(USER_SERVICE + "/user/getUserNameById", id, String.class);*/
        SingleResponse obj = restTemplate.getForObject(USER_SERVICE + "/user/getUserNameById?id=" + id, SingleResponse.class);
        log.info("发送 get 请求 只取返回值时，返回值是： " + obj);
        ResponseEntity<SingleResponse> entity = restTemplate.getForEntity(USER_SERVICE + "/user/getUserNameById?id=" + id, SingleResponse.class);
        log.info("发送 get 请求 取整个返回结果，返回的entity是：" + entity);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return R.error();
        }
    }
}
