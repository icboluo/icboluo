package com.icboluo.util;

/**
 * @author icboluo
 * @since 2024-06-28 上午12:41
 */
public interface CacheItem<K> {
    String getName(K v);

    K getKey();
}
