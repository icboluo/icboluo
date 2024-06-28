package com.icboluo.util;


import com.github.benmanes.caffeine.cache.Cache;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 简单缓存操作
 *
 * @author icboluo
 * @since 2022-11-26 10:29
 */
public interface CacheTemplate<K, T extends CacheItem<K>> {

    /**
     * 缓存类型
     */
    Cache<K, T> cache();

    List<T> selectByKeys(List<K> ids);

    default Optional<String> findNameByCode(K code) {
        Optional<T> item = findByCode(code);
        return item.map(CacheItem::getName);
    }

    default void findToCache(Stream<K> codeStream) {
        Cache<K, T> cache = cache();
        // 获取缓存中没有的数据
        List<K> cacheNotHave = codeStream.filter(key -> !ObjectUtils.isEmpty(key))
                .distinct()
                .filter(code -> cache.getIfPresent(code) == null)
                .toList();
        // 如果缓存中全部存在，不做处理
        if (cacheNotHave.isEmpty()) {
            return;
        }
        // 否则将不存在的数据查询出来打进缓存
        List<T> dbList = selectByKeys(cacheNotHave);
        for (T db : dbList) {
            cache.put(db.getKey(), db);
        }
    }

    default Optional<T> findByCode(K id) {
        return findByCode(id, null);
    }

    default Optional<T> findByCode(K id, Function<K, T> generateInvalidate) {
        Cache<K, T> cache = cache();
        if (ObjectUtils.isEmpty(id)) {
            return Optional.empty();
        }
        // 如果缓存中存在，直接返回
        T cacheData = cache.getIfPresent(id);
        if (cacheData != null) {
            return Optional.of(cacheData);
        }
        // 缓存中不存在，从db中获取返回
        List<T> items = selectByKeys(Collections.singletonList(id));
        if (!items.isEmpty()) {
            T db = items.get(0);
            cache.put(id, db);
            return Optional.of(db);
        }
        // 缓存无效数据
        if (generateInvalidate != null) {
            T invalidateData = generateInvalidate.apply(id);
            cache.put(id, invalidateData);
            return Optional.of(invalidateData);
        }
        return Optional.empty();
    }

    /**
     * 使无效
     *
     * @param keys 键集合
     */
    default void invalidate(List<K> keys) {
        Cache<K, T> cache = cache();
        BeanUtil.batchConsumer(keys, cache::invalidateAll);
    }
}
