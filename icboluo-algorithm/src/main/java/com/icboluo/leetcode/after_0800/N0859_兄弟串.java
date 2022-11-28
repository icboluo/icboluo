package com.icboluo.leetcode.after_0800;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-11-25 14:32
 */
class N0859_兄弟串 {
    // 交换字母使其成为goal
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            HashSet<Character> set = new HashSet<>();
            // 此块仅仅是为了判断字符串是否有重复字符，可以使用contain提前结束循环
            for (int i = 0; i < s.length(); i++) {
                set.add(s.charAt(i));
            }
            return set.size() < s.length();
        }
        // 找出不同的字符
        List<Integer> different = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                different.add(i);
            }
        }
        // 判断字符的idx是否合理
        return different.size() == 2 && s.charAt(different.get(0)) == goal.charAt(different.get(1))
                && s.charAt(different.get(1)) == goal.charAt(different.get(0));
    }

    // 1657 确定2个字符串是否接近
    public boolean closeStrings(String word1, String word2) {
        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            word1Map.put(word1.charAt(i), word1Map.getOrDefault(word1.charAt(i), 0) + 1);
        }
        for (int i = 0; i < word2.length(); i++) {
            word2Map.put(word2.charAt(i), word2Map.getOrDefault(word2.charAt(i), 0) + 1);
        }
        // 保证a/b里面的字符均在对方有出现
        if (!word1Map.keySet().equals(word2Map.keySet())) {
            return false;
        }
        // 保证字符出现的模式是相等的，比如都是出现了1 5 7 次
        List<Integer> word1FrenquencyList = word1Map.values().stream().sorted().toList();
        List<Integer> word2FrenquencyList = word2Map.values().stream().sorted().toList();
        return word1FrenquencyList.equals(word2FrenquencyList);
    }

    // 1790 检查一次字符串交换是否可以使字符串相等 TODO ERROR
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        Integer different = null;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (different == null) {
                    different = i;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
