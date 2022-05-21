package com.icboluo.producer;

import com.icboluo.annotation.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author icboluo
 * @since 2020-08-27 19:26
 */
@RestController
@RequestMapping("/user")
@ResponseResult
@Slf4j
public class UserProducer {

    @GetMapping("/getUserNameById")
    public String getUserNameById(@RequestParam Integer id) {
        log.info("GET请求，进入icboluo-user服务了");
        Objects.requireNonNull(id, "id不能为空");
        if (id <= 10) {
            return "user: " + id;
        } else {
            return null;
        }
    }

    @PostMapping("/postUserNameById")
    public String postUserNameById(@RequestBody Integer id) {
        log.info("POST请求，进入icboluo-user服务了");
        Objects.requireNonNull(id, "id不能为空");
        if (id <= 10) {
            return "user: " + id;
        } else {
            return null;
        }
    }
}
