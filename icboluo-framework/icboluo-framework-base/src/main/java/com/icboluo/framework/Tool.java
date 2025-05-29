package com.icboluo.framework;

import java.util.TreeMap;

/**
 * @author icboluo
 * @since 2025-05-29 21:18
 */
public class Tool {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 3);
        map.put(5, 3);
        map.put(2, 6);
        map.put(4, 4);
        map.put(3, 1);
        // TreeMap values() 的顺序合keySet() 的顺序是一一对应的
        System.out.println(map.keySet());
        System.out.println(map.values());
        // parallelStream 的顺序是不固定的 965.ok
        map.keySet().parallelStream().filter(a -> a < 10).forEach(System.out::println);
    }
}
