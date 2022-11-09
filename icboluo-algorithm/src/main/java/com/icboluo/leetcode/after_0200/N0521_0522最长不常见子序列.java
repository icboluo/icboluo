package com.icboluo.leetcode.after_0200;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-11-07 21:14
 */
class N0521_0522最长不常见子序列 {
    /**
     * 题意：最长，字符串最长；不常见，只出现一次；子序列，这个是废话，a是b的子序列，b不是a的子序列，所以依然不是子序列，相当于equals
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }

    // TODO ERROR
    public int findLUSlength(String[] strs) {
        // 字符串，出现次数的map
        Map<String, Integer> strCountMap = Arrays.stream(strs).collect(Collectors.groupingBy(key -> key, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        return strCountMap.entrySet().stream()
                // 找出只出现一次的
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .map(String::length)
                // 最长的
                .min((a, b) -> b - a)
                .orElse(-1);
    }
}
