package com.icboluo.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO error please update
 *
 * @author icboluo
 * @since 2021-59-28 23:59
 */
public class KkvMap<K1, K2, V> {

    private final Map<List<Object>, V> map;

    public KkvMap() {
        map = new HashMap<>();
    }

    public void put(K1 k1, K2 k2, V v) {
        List<Object> key = Arrays.asList(k1, k2);
        map.put(key, v);
    }

    public V get(K1 k1, K2 k2) {
        List<Object> key = Arrays.asList(k1, k2);
        return map.get(key);
    }
}
