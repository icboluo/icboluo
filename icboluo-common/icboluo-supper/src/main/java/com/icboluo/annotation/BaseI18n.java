package com.icboluo.annotation;

import lombok.Data;

/**
 * @author icboluo
 * @since 2024-06-28 上午1:13
 */
@Data
public class BaseI18n implements CacheItem<String> {
    private String key;
    private String name;
}
