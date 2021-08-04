package com.icboluo.config;

import com.icboluo.common.Constant;
import com.icboluo.util.redis.RedisList;
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
        redisList.del(Constant.unit);
        redisList.delKeys(Constant.unit);
    }
}
