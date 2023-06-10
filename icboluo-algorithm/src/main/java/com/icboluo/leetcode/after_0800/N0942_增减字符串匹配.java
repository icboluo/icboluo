package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2020-10-11 16:32
 */
class N0942_增减字符串匹配 {
    // DI字符串匹配 incr decr 匹配
    public int[] diStringMatch(String s) {
        int[] res = new int[s.length() + 1];
        int left = 0;
        int right = res.length - 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                res[i] = left++;
            } else {
                res[i] = right--;
            }
        }
        res[res.length - 1] = left;
        return res;
    }
}
