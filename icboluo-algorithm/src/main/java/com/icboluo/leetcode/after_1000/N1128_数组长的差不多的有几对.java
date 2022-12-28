package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-12-28 22:59
 */
class N1128_数组长的差不多的有几对 {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<String, Integer> eleCountMap = Arrays.stream(dominoes)
                .peek(Arrays::sort)
                .map(arr -> arr[0] + "-" + arr[1])
                .collect(Collectors.groupingBy(key -> key, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        return eleCountMap.values().stream().map(this::jieChen).mapToInt(Integer::intValue).sum();
    }

    private int jieChen(int n) {
        return n * (n - 1) / 2;
    }
}
