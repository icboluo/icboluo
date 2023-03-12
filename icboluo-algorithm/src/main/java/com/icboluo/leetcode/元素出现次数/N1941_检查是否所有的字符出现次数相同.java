package com.icboluo.leetcode.元素出现次数;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-13 2:15
 */
class N1941_检查是否所有的字符出现次数相同 {
    public boolean areOccurrencesEqual(String s) {
        Map<Character, Integer> eleCountMap = IntStream.range(0, s.length())
                .mapToObj(s::charAt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
        return eleCountMap.values().stream().distinct().count() == 1;
    }
}
