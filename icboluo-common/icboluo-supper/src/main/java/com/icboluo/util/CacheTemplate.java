package com.icboluo.util;


import com.github.benmanes.caffeine.cache.Cache;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author icboluo
 * @since 2022-11-26 10:29
 */
@AllArgsConstructor
public class CacheTemplate<K, V> {

    /**
     * 缓存类型
     */
    private Cache<K, V> cache;

    /**
     * ID获取函数
     */
    private Function<V, K> idGetFun;

    /**
     * 根据id查询函数
     */
    private Function<K, V> selectByIdFun;

    /**
     * 根据id批量查询函数
     */
    private Function<List<K>, List<V>> selectByIdsFun;

    public void findToCache(Stream<K> codeStream) {
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
        List<V> dbList = selectByIdsFun.apply(cacheNotHave);
        for (V db : dbList) {
            cache.put(idGetFun.apply(db), db);
        }
    }

    public Optional<V> findByCode(K id) {
        return findByCode(id, null);
    }

    public Optional<V> findByCode(K id, Function<K, V> generateInvalidate) {
        if (ObjectUtils.isEmpty(id)) {
            return Optional.empty();
        }
        // 如果缓存中存在，直接返回
        V cacheData = cache.getIfPresent(id);
        if (cacheData != null) {
            return Optional.of(cacheData);
        }
        // 缓存中不存在，从db中获取返回
        V db = selectByIdFun.apply(id);
        if (db != null) {
            cache.put(id, db);
            return Optional.of(db);
        }
        // 缓存无效数据
        if (generateInvalidate != null) {
            V invalidateData = generateInvalidate.apply(id);
            cache.put(id, invalidateData);
            return Optional.of(invalidateData);
        }
        return Optional.empty();
    }

    public void invalidate(K k) {
        cache.invalidate(k);
    }
}
