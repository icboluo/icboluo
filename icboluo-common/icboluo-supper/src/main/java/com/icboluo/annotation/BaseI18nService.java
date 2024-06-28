package com.icboluo.annotation;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.icboluo.util.CacheTemplate;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author icboluo
 * @since 2024-06-28 上午1:12
 */
@Service
public class BaseI18nService implements CacheTemplate<String, BaseI18n> {

    Cache<String, BaseI18n> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();

    @Override
    public Cache<String, BaseI18n> cache() {
        return cache;
    }

    @Override
    public List<BaseI18n> selectByKeys(List<String> ids) {
        return List.of();
    }
}
