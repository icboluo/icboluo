package com.icboluo.leetcode.low;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-12-28 20:26
 */
class N1287_出现次数超过百分之25的数组 {
    public int findSpecialInteger(int[] arr) {
        Map<Integer, Integer> eleCountMap = new HashMap<>();
        for (int num : arr) {
            eleCountMap.put(num, eleCountMap.getOrDefault(num, 0) + 1);
            if (eleCountMap.get(num) > arr.length / 4) {
                return num;
            }
        }
        // 其实用map倒序也可以
        return 0;
    }
}
