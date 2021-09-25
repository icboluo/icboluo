package com.icboluo.config;

import com.icboluo.common.Constant;
import com.icboluo.common.redis.RedisList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author icboluo
 * @date 2021-47-01 22:47
 */
@Component
@Profile("!test")
public class RedisCacheConfig {

    @Resource
    private RedisList<Object> redisList;

    @PostConstruct
    public void del() {
        long del = redisList.del(Constant.UNIT);
        long l = redisList.delKeys(Constant.UNIT);
        System.out.println(del);
        System.out.println(l);
    }
}
