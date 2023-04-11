package com.icboluo.leetcode.元素出现次数;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-02-15 22:52
 */
class N1624_两个相等字符之间的最大子串 {
    /**
     * 元素出现的上一个位置
     * 题目要求是计算首次出现和最终出现的距离，这个方法计算的是上一个位置，是不合理的
     *
     * @param s
     * @return
     */
    public int maxLengthBetweenEqualCharacters1(String s) {
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE / 2);
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            int temp = i - arr[s.charAt(i) - 'a'] - 1;
            max = Math.max(max, temp);
            arr[s.charAt(i) - 'a'] = i;
        }
        return max;
    }

    public int maxLengthBetweenEqualCharacters2(String s) {
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE / 2);
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            int temp = i - arr[s.charAt(i) - 'a'] - 1;
            max = Math.max(max, temp);
            // 记录首次
            if (arr[s.charAt(i) - 'a'] == Integer.MAX_VALUE / 2) {
                arr[s.charAt(i) - 'a'] = i;
            }
        }
        return max;
    }
}
