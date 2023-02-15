package com.icboluo.leetcode.元素出现次数;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-02-15 22:52
 */
class N1624_两个相等字符之间的最大子串 {
    // 元素出现的上一个位置 FIXME ERROR
    public int maxLengthBetweenEqualCharacters(String s) {
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
}
