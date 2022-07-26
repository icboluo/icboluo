package com.icboluo.leetcode.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-07-27 0:36
 */
public class N0076_最小覆盖子串 {

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> win = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int l = 0;
        int r = 0;
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (r < s.length()) {
            char right = s.charAt(r);
            r++;
            if (need.containsKey(right)) {
                win.put(right, win.getOrDefault(right, 0) + 1);
                if (win.get(right).equals(need.get(right))) {
                    valid++;
                }
            }
            while (valid == need.size()) {
                if (r - l < len) {
                    start = l;
                    len = r - l;
                }
                char left = s.charAt(l);
                l++;
                if (need.containsKey(left)) {
                    if (need.get(left).equals(win.get(left))) {
                        valid--;
                    }
                    win.put(left, win.getOrDefault(left, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
