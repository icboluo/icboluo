package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-12-15 22:53
 */
class N1122_按照数组2的顺序排序数组1 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        return Arrays.stream(arr1).boxed().sorted((a, b) -> {
            int aIdx = map.getOrDefault(a, Integer.MAX_VALUE);
            int bIdx = map.getOrDefault(b, Integer.MAX_VALUE);
            if (aIdx != bIdx) {
                return aIdx - bIdx;
            }
            return a - b;
        }).mapToInt(Integer::intValue).toArray();
    }
}
