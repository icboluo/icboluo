package com.icboluo.leetcode.after_1000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-12-08 22:11
 */
class N1051_高度检查器 {
    // 就是简简单单的比较2个数组的不同
    public int heightChecker(int[] heights) {
        int[] arr = Arrays.copyOf(heights, heights.length);
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (heights[i] != arr[i]) {
                count++;
            }
        }
        return count;
    }
}
