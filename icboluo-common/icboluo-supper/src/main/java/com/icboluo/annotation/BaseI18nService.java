package com.icboluo.annotation;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.icboluo.util.BaseI18n;
import com.icboluo.util.CacheTemplate;
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

    @Override
    public String getKey(BaseI18n item) {
        return item.getKey();
    }

    @Override
    public String getName(BaseI18n item) {
        return item.getName();
    }
}
