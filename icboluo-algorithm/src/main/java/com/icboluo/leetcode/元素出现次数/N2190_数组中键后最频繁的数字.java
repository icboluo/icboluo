package com.icboluo.leetcode.元素出现次数;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-03-21 22:55
 */
class N2190_数组中键后最频繁的数字 {
    /**
     * 把目标值为k的字符后一个字符保留下来，求最频繁的 FIXME ERROR
     * 元素出现次数的另一种解法
     *
     * @param nums
     * @param key
     * @return
     */
    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> eleCountMap = new HashMap<>();
        int max = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key && eleCountMap.merge(nums[i + 1], 1, Integer::sum) > eleCountMap.get(max)) {
                max = eleCountMap.get(nums[i + 1]);
            }
        }
        return max;
    }
}
