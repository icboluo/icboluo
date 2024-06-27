package com.icboluo.annotation;

import com.github.benmanes.caffeine.cache.Cache;
import com.icboluo.util.CacheTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author icboluo
 * @since 2024-06-28 上午1:12
 */
@Service
public class BaseI18nService implements CacheTemplate<String, BaseI18n> {
    @Override
    public Cache<String, BaseI18n> cache() {
        return null;
    }

    @Override
    public List<BaseI18n> selectByKeys(List<String> ids) {
        return List.of();
    }
}
