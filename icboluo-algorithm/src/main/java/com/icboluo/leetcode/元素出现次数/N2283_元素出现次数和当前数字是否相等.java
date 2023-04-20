package com.icboluo.leetcode.元素出现次数;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-20 22:03
 */
class N2283_元素出现次数和当前数字是否相等 {
    public boolean digitCount(String num) {
        Map<Integer, Integer> eleCountMap = IntStream.range(0, num.length())
                .mapToObj(num::charAt)
                .map(ch -> Character.digit(ch, 10))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
        for (int i = 0; i < num.length(); i++) {
            if (Character.digit(num.charAt(i), 10) != eleCountMap.getOrDefault(i, 0)) {
                return false;
            }
        }
        return true;
    }
}
