package com.icboluo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @date 2021-41-28 23:41
 */
public class KvvMap<K, V1, V2> {

    private final Map<K, V1> map1;

    private final Map<K, V2> map2;

    public KvvMap() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }

    public void put(K k, V1 v1, V2 v2) {
        map1.put(k, v1);
        map2.put(k, v2);
    }

    public V1 get1(K k) {
        return map1.get(k);
    }

    public V2 get2(K k) {
        return map2.get(k);
    }
}
