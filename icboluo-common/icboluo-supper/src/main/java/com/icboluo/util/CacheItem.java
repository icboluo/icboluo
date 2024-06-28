package com.icboluo.util;

/**
 * 缓存项
 *
 * @author icboluo
 * @since 2024-05-21 22:47
 */
public interface CacheItem<K> {

    /**
     * 获取key
     *
     * @return key
     */
    K getKey();

    /**
     * 获取名称
     *
     * @return 名称
     */
    String getName();
}

