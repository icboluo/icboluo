package com.icboluo.leetcode.after_0800;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-22 21:57
 */
class N0976_最大三角形 {

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            // 三角形仅仅需要满足2边之和大于第三边即可，不满足说明第一个值太大了，缩小之和再试即可
            if (nums[i] < nums[i - 1] + nums[i - 2]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }

    // 0812 三角形的最大面积 it`s hard to understand
}
