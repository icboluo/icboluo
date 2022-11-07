package com.icboluo.leetcode.after_0600;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-07 22:24
 */
class N0821_到一个字符的最短距离 {
    public int[] shortestToChar(String s, char c) {
        int[] arr = new int[s.length()];
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == c) {
                arr[i] = 0;
                // 中心开花
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] > i - j) {
                        arr[j] = i - j;
                    } else {
                        break;
                    }
                }
                for (int j = i + 1; j < s.length(); j++) {
                    if (arr[j] > j - i) {
                        arr[j] = j - i;
                    } else {
                        break;
                    }
                }
            }
        }
        return arr;
    }
}
