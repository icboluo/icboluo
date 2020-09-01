package com.icboluo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author icboluo
 * @date 2020-08-27 19:26
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserProducer {

    @GetMapping("/getUserNameById")
    public String getUserNameById(@RequestParam Integer id) {
        log.info("进入icboluo-user服务了");
        Objects.requireNonNull(id, "id不能为空");
        if (id <= 10) {
            return "user: " + id;
        } else {
            return null;
        }
    }
}