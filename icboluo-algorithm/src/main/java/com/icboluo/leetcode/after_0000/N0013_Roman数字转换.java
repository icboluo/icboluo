package com.icboluo.leetcode.after_0000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-25 16:03
 */
class N0013_Roman数字转换 {
    public static void main(String[] args) {
        N0013_Roman数字转换 cla = new N0013_Roman数字转换();
        int iii = cla.romanToInt("III");
        System.out.println("iii = " + iii);
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ans = 0;
        for (int i = 0; i < s.toCharArray().length - 1; i++) {
            int cur = map.get(s.charAt(i));
            int next = map.get(s.charAt(i + 1));
            if (cur > next) {
                ans += cur;
            } else {
                ans -= cur;
            }
        }
        ans += map.get(s.charAt(s.length() - 1));
        return ans;
    }
}
