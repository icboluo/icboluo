package com.icboluo.leetcode.after_1000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-12-28 22:17
 */
class N1189_最大可以构成目标字符串多少次 {
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> eleCountMap = toMap(text);
        Map<Character, Integer> needMap = toMap("balloon");
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : needMap.entrySet()) {
            Integer needVal = entry.getValue();
            Integer shiJiVal = eleCountMap.getOrDefault(entry.getKey(), 0);
            min = Math.min(min, shiJiVal / needVal);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private Map<Character, Integer> toMap(String str) {
        Map<Character, Integer> eleCountMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            eleCountMap.put(str.charAt(i), eleCountMap.getOrDefault(str.charAt(i), 0) + 1);
        }
        return eleCountMap;
    }
}
