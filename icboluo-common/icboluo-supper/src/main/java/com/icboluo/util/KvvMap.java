package com.icboluo.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-41-28 23:41
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

    public static void main(String[] args) {
        Multimap<String, String> kvvMap = ArrayListMultimap.create();
        kvvMap.put("key", "1");
        kvvMap.put("key", "2");
        Collection<String> key = kvvMap.get("key");
        System.out.println("key = " + key);

        Table<Integer, String, String> kkvMap = HashBasedTable.create();
        kkvMap.put(18, "nan", "xiaoming");
        kkvMap.put(18, "nv", "xiaohong");
        String nv = kkvMap.get(18, "nv");
        System.out.println("nv = " + nv);
        Map<String, String> row = kkvMap.row(18);
        System.out.println("row = " + row);
        Map<Integer, String> nv1 = kkvMap.column("nv");
        System.out.println("nv1 = " + nv1);
    }
}
