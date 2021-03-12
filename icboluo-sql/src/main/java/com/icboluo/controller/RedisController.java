package com.icboluo.controller;

import com.icboluo.util.redis.RedisString;
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
    private RedisString<String> redisString;

    @GetMapping("/redisSetWithTime")
    public boolean redisSetWithTime() {
        return redisString.set("test", "this test", 10, TimeUnit.SECONDS);
    }

    @GetMapping("/redisGet")
    public String redisGet() {
        LocalDateTime now = LocalDateTime.now();
        return redisString.getOfNullAble("test");
    }

    @GetMapping("/redisSet")
    public Response redisSet() {
        boolean set = redisString.set("test", "this test");
        return R.correct(set);
    }

    @GetMapping("/add")
    public Long add() {
        return redisString.increment("add", Long.class);
    }

    @GetMapping("/expireAt")
    public Response expireAt() {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(10);
        Long add = redisString.incrementExpireAt("add", Long.class, localDateTime);
        Long expire = redisString.getExpire("add");
        return R.correct(add);
    }

    @GetMapping("/decr")
    public Long decr() {
        return redisString.decrease("add", Long.class);
    }
}
