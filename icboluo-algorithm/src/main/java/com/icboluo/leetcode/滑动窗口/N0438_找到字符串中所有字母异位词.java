package com.icboluo.leetcode.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-01 12:55
 */
class N0438_找到字符串中所有字母异位词 {
    public static void main(String[] args) {
        N0438_找到字符串中所有字母异位词 cla = new N0438_找到字符串中所有字母异位词();
        String str1 = "cbaebabacd";
        String str2 = "abc";
        List<Integer> list = cla.findAnagrams(str1, str2);
        System.out.println("list = " + list);
    }

    // TODO error
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resList = new ArrayList<>();
        // 字符出现的次数
        Map<Character, Integer> winMap = new HashMap<>();
        Map<Character, Integer> needMap = new HashMap<>();
        char[] chars2 = p.toCharArray();
        for (char ch : chars2) {
            needMap.put(ch, needMap.getOrDefault(ch, 0) + 1);
        }
        int l = 0;
        int r = 0;
        int match = 0;
        while (r < s.length()) {
            char right = s.charAt(r++);
            // 扩大最右边界
            if (needMap.containsKey(right)) {
                winMap.put(right, winMap.getOrDefault(right, 0) + 1);
                if (winMap.get(right).equals(needMap.get(right))) {
                    match++;
                }
            }
            // 缩小滑动窗口值，得到最优解：首要条件是先得是最优解
            // 这个while和if换一下也是可以的
            while (needMap.size() == match) {
                // 更新结果集
                if (r - l - 1 == needMap.size()) {
                    resList.add(l);
                }
                char left = s.charAt(l++);
                if (needMap.containsKey(left)) {
                    winMap.put(left, winMap.getOrDefault(left, 0) - 1);
                    if (winMap.get(left) < needMap.get(left)) {
                        match--;
                    }
                }
            }
        }
        return resList;
    }
}
