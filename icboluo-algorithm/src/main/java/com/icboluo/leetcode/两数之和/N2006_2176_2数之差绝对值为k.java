package com.icboluo.leetcode.两数之和;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-16 22:40
 */
class N2006_2176_2数之差绝对值为k {

    // 求数组中2值之差的绝对值为k的个数 2数之和的问题 FIXME ERROR
    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> eleCountMap = IntStream.range(0, nums.length)
                .boxed()
                .collect(Collectors.toMap(i -> nums[i], ele -> 1, Integer::sum));
        int res = 0;
        for (int num : nums) {
            int a = Math.abs(num - k);
            int b = Math.abs(num + k);
            res += eleCountMap.getOrDefault(a, 0) + eleCountMap.getOrDefault(b, 0);
        }
        return res / 2;
    }

    // 2176 相同元素的索引的乘积是否能整除k
    public int countPairs(int[] nums, int k) {
        Map<Integer, List<Integer>> eleIdxMap = IntStream.range(0, nums.length)
                .boxed()
                .collect(Collectors.groupingBy(i -> nums[i]));

        int res = 0;
        for (Map.Entry<Integer, List<Integer>> entry : eleIdxMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                continue;
            }
            for (int i = 0; i < entry.getValue().size() - 1; i++) {
                for (int j = i + 1; j < entry.getValue().size(); j++) {
                    if (entry.getValue().get(i) * entry.getValue().get(j) % k == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
