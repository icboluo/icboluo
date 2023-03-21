package com.icboluo.leetcode.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-03-02 21:10
 */
class N1876_子串中固定长度不重复的数量 {
    // 子串长度为3，且子串中字符不重复的数量,简简单单的滑动窗口
    public int countGoodSubstrings(String s) {
        int l = 0;
        int r = 0;
        Map<Character, Integer> winMap = new HashMap<>();
        int count = 0;
        while (r < s.length()) {
            char right = s.charAt(r++);
            while (winMap.containsKey(right)) {
                winMap.remove(s.charAt(l++));
            }
            winMap.put(right, r);
            if (winMap.size() >= 3) {
                count++;
            }
        }
        return count;
    }
}
