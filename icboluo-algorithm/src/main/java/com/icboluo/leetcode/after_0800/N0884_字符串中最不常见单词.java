package com.icboluo.leetcode.after_0800;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-12-05 21:28
 */
class N0884_字符串中最不常见单词 {
    public String[] uncommonFromSentences(String s1, String s2) {
        String[] arr1 = s1.split(" ");
        String[] arr2 = s2.split(" ");
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (String s : arr1) {
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }
        for (String s : arr2) {
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }
        Set<String> set1 = map1.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        Set<String> set2 = map2.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        List<String> res = new ArrayList<>();
        for (String s : set1) {
            if (!map2.containsKey(s)) {
                res.add(s);
            }
        }
        for (String s : set2) {
            if (!map1.containsKey(s)) {
                res.add(s);
            }
        }
        return res.toArray(new String[0]);
    }
}
