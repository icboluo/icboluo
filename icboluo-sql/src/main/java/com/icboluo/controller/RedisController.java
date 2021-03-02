package com.icboluo.controller;

import com.icboluo.util.redis.StringRedis;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
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
    public Response redisSet() {
        boolean set = stringRedis.set("test", "this test");
        return R.correct(set);
    }

    @GetMapping("/add")
    public Long add() {
        return stringRedis.increment("add", Long.class);
    }

    @GetMapping("/expireAt")
    public Response expireAt() {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(10);
        Long add = stringRedis.incrementExpireAt("add", Long.class, localDateTime);
        Long expire = stringRedis.getExpire("add");
        return R.correct(add);
    }

    @GetMapping("/decr")
    public Long decr() {
        return stringRedis.decrease("add", Long.class);
    }
}
