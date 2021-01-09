package com.icboluo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisConfig {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/redisSetWithTime")
    public boolean redisSetWithTime() {
        try {
            redisTemplate.opsForValue().set("test", "this test", 10, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/redisGet")
    public Object redisGet() {
        return redisTemplate.opsForValue().get("test");
    }

    @GetMapping("/redisSet")
    public boolean redisSet() {
        try {
            redisTemplate.opsForValue().set("test", "this test");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
