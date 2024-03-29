package com.icboluo.config;

import com.icboluo.common.Constant;
import com.icboluo.common.redis.RedisList;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 非 simple 环境，生效
 *
 * @author icboluo
 * @since 2021-47-01 22:47
 */
@Component
@Profile("!simple")
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
