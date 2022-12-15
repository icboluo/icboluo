package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-12-15 23:00
 */
class N1207_每个val出现的次数是否唯一 {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> eleCountMap = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(key -> key, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        return eleCountMap.values().stream().distinct().count() == eleCountMap.size();
    }
}
