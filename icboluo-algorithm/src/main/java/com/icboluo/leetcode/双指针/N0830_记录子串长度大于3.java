package com.icboluo.leetcode.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-21 23:38
 */
class N0830_记录子串长度大于3 {
    /**
     * 字符串中，记录子串长度大于3，双指针问题
     *
     * @param s
     * @return
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        int l = 0;
        int r = 0;
        List<List<Integer>> res = new ArrayList<>();
        while (r < s.length()) {
            while (r < s.length() && s.charAt(l) == s.charAt(r)) {
                r++;
            }
            if (r - l >= 3) {
                res.add(Arrays.asList(l, r - 1));
            }
            l = r;
        }
        return res;
    }
}
