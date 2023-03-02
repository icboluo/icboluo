package com.icboluo.leetcode.元素出现次数;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-03-01 18:56
 */
public class _寻找第一个最大出现次数的元素 {
    public static void main(String[] args) {
        System.out.println(firstMostCount(new int[]{1, 1, 2, 3, 3, 9, 5}));
    }

    public static int firstMostCount(int[] arr) {
        // 元素出现次数的键值对
        Map<Integer, Integer> eleCountMap = new HashMap<>();
        // 最大出现次数的元素
        int maxCount = Integer.MIN_VALUE;
        // 最大出现次数元素的索引
        int maxIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            eleCountMap.put(arr[i], eleCountMap.getOrDefault(arr[i], 0) + 1);
            // 只有大于的时候更新结果，保证最优值会在最左边出现
            if (eleCountMap.get(arr[i]) > maxCount) {
                maxCount = eleCountMap.get(arr[i]);
                maxIdx = i;
            }
        }
        return arr[maxIdx];
    }
}
