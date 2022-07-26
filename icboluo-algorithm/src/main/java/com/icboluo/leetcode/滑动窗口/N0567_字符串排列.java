package com.icboluo.leetcode.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-07-27 0:54
 */
public class N0567_字符串排列 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> win = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int l = 0;
        int r = 0;
        int valid = 0;
        while (r < s2.length()) {
            char right = s2.charAt(r);
            r++;
            if (need.containsKey(right)) {
                win.put(right, win.getOrDefault(right, 0) + 1);
                if (need.get(right).equals(win.get(right))) {
                    valid++;
                }
            }
            while (r - l >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char left = s2.charAt(l);
                l++;
                if (need.containsKey(left)) {
                    if (need.get(left).equals(win.get(left))) {
                        valid--;
                    }
                    win.put(left, win.get(left) - 1);
                }
            }
        }
        return false;
    }
}
