package com.icboluo.leetcode.low;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-06-05 21:36
 */
class N0961_数组中出现次数一半的元素 {
    // 元素出现次数 low
    // 其实也可以用别的判断：1个元素重复n次，其余元素就每个只出现1次，只要判断哪个元素出现多次即可
    public int repeatedNTimes(int[] nums) {
        Map<Integer, Long> eleCountMap = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return eleCountMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == nums.length / 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    }
}
