package com.icboluo.leetcode.两数之和;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-06-05 23:26
 */
class N0454_4数相加 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 22求和求概率
        Map<Integer, Integer> eleCountMap = new HashMap<>();
        Arrays.stream(nums1)
                .forEach(num1 -> Arrays.stream(nums2)
                        .forEach(num2 -> eleCountMap.compute(num1 + num2, (k, count) -> count == null ? 1 : ++count)));
        return Arrays.stream(nums3)
                // 相反数
                .map(num3 -> Arrays.stream(nums4).map(num4 -> eleCountMap.getOrDefault(-(num3 + num4), 0)).sum())
                .sum();
    }
}
