package com.icboluo.leetcode.low;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-23 23:49
 */
class N1920_基于排列构建数组 {
    /**
     * 使用元素当做索引重新构建
     *
     * @param nums
     * @return
     */
    public int[] buildArray(int[] nums) {
        return Arrays.stream(nums).map(num -> nums[num]).toArray();
    }
}
