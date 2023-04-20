package com.icboluo.leetcode.两数之和;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-04-20 23:04
 */
class N2395_查找和相等的子数组 {
    /**
     * 连续2个数字之和是否相等
     *
     * @param nums
     * @return
     */
    public boolean findSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (!set.add(nums[i] + nums[i + 1])) {
                return true;
            }
        }
        return false;
    }
}
