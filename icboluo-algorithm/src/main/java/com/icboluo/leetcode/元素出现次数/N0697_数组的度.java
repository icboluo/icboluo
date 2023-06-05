package com.icboluo.leetcode.元素出现次数;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-06-05 21:30
 */
class N0697_数组的度 {
    // 元素出现次数最多，且数组长度最小的子数组
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> eleCountMap = new HashMap<>();
        Map<Integer, Integer> firstMap = new HashMap<>();
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            firstMap.putIfAbsent(nums[i], i);
            eleCountMap.put(nums[i], eleCountMap.getOrDefault(nums[i], 0) + 1);
            if (eleCountMap.get(nums[i]) > count) {
                count = eleCountMap.get(nums[i]);
                res = i - firstMap.get(nums[i]) + 1;
            } else if (eleCountMap.get(nums[i]) == count) {
                res = Math.min(res, i - firstMap.get(nums[i]) + 1);
            }
        }
        return res;
    }
}
