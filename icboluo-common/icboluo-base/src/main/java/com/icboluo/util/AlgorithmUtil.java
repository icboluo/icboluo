package com.icboluo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author icboluo
 * @since 2021-26-24 21:26
 */
public class AlgorithmUtil {

    public static void main(String[] args) {
        int res = a(1);
        Integer integer = cacheAlgorithm(
                1,
                new HashMap<>(),
                AlgorithmUtil::a
        );
        System.out.println("integer = " + integer);
    }

    private static Integer a(int i) {
        return 3;
    }

    public static <K, V> V cacheAlgorithm(K key, Map<K, V> map, Function<K, V> function) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        V val = function.apply(key);
        map.put(key, val);
        return val;
    }
}
