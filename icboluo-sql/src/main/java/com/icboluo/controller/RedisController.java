package com.icboluo.controller;

import com.icboluo.redis.StringRedis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author icboluo
 */
@RestController
public class RedisController {

    @Resource
    private StringRedis<String> stringRedis;

    @GetMapping("/redisSetWithTime")
    public boolean redisSetWithTime() {
        return stringRedis.set("test", "this test", 10, TimeUnit.SECONDS);
    }

    @GetMapping("/redisGet")
    public String redisGet() {
        LocalDateTime now = LocalDateTime.now();
        return stringRedis.getOfNullAble("test");
    }

    @GetMapping("/redisSet")
    public boolean redisSet() {
        return stringRedis.set("test", "this test");
    }

    @GetMapping("/add")
    public Long add() {
        return stringRedis.increment("add", 1L);
    }

    @GetMapping("/decr")
    public Long decr() {
        return stringRedis.decrease("add", 1L);
    }
}
