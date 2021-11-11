package com.icboluo.controller;

import com.icboluo.feign.UserFeign;
import com.icboluo.service.impl.UserService;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author icboluo
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserFeign userFeign;

    @Resource
    private RestTemplate restTemplate;

    private static final String USER_SERVICE = "http://localhost:7010";

    @GetMapping("/login")
    public boolean login(@RequestParam String name, @RequestParam String password) {
        return userService.validate(name, password);
    }

    @GetMapping("/updatePassword")
    public boolean updatePassword(@RequestParam Integer id, @RequestParam String password) {
        return userService.updatePassword(id, password);
    }

    @GetMapping("/feign/getUserNameById")
    public String feignGetUserNameById(@RequestParam Integer id) {
        return userFeign.getUserNameById(id);
    }

    @GetMapping("/restTemplate/getUserNameById")
    public Response restTemplateGetUserNameById(@RequestParam Integer id) {
/*        这个是放在请求体中传输的
        String obj = restTemplate.postForObject(USER_SERVICE + "/user/getUserNameById", id, String.class);*/
        String obj = restTemplate.getForObject(USER_SERVICE + "/user/getUserNameById?id=" + id, String.class);
        log.info("发送 get 请求 只取返回值时，返回值是： " + obj);
        ResponseEntity<String> entity = restTemplate.getForEntity(USER_SERVICE + "/user/getUserNameById?id=" + id, String.class);
        log.info("发送 get 请求 取整个返回结果，返回的entity是：" + entity);
        if (entity.getStatusCode().is2xxSuccessful()) {
            //TODO 返回值包装异常
            return R.correct(entity.getBody());
        } else {
            return R.error();
        }
    }
}
