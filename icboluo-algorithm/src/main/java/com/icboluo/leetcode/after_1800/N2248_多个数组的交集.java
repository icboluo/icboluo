package com.icboluo.leetcode.after_1800;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-22 12:28
 */
class N2248_多个数组的交集 {
    public List<Integer> intersection(int[][] nums) {
        Map<Integer, Integer> itemCountMap = new HashMap<>();
        for (int[] num : nums) {
            for (int i : num) {
                itemCountMap.put(i, itemCountMap.getOrDefault(i, 0) + 1);
            }
        }
        return itemCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == nums.length)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }
}
