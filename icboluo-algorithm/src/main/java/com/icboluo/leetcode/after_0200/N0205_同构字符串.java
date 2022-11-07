package com.icboluo.leetcode.after_0200;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-03 21:26
 */
class N0205_同构字符串 {
    public boolean isIsomorphic(String s, String t) {
// ascii 大小是256
        int[] arr1 = new int[256];
        int[] arr2 = new int[256];
        Arrays.fill(arr1, -1);
        Arrays.fill(arr2, -1);
        for (int i = 0; i < s.length(); i++) {
            // 上一个字符出现的位置
            if (arr1[s.charAt(i)] != arr2[t.charAt(i)]) {
                return false;
            }
            arr1[s.charAt(i)] = i;
            arr2[t.charAt(i)] = i;
        }
        return true;
    }
}
