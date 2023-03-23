package com.icboluo.leetcode.after_1700;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-03-23 23:18
 */
class N1995_统计特殊四元组 {
    /**
     * 数组里面3个数之和等于另一个数的个数 TODO HARD
     *
     * @param nums
     * @return
     */
    public int countQuadruplets(int[] nums) {
        int res = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(nums[nums.length - 1] - nums[nums.length - 2], 1);
        for (int i = nums.length - 3; i >= 0; i--) {

        }
        return res;
    }
}
