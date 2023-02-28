package com.icboluo.leetcode.after_1700;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-02-21 21:30
 */
class N1748_唯一元素的和 {
    // 唯一元素总和
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> eleCountMap = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), ele -> 1, Integer::sum));
        return eleCountMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
