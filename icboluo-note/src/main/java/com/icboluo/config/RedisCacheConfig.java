package com.icboluo.config;

import com.icboluo.common.Constant;
import com.icboluo.util.redis.ListRedis;
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
    private ListRedis listRedis;

    @PostConstruct
    public void del() {
        listRedis.del(Constant.unit);
        listRedis.delKeys(Constant.unit);
    }
}
