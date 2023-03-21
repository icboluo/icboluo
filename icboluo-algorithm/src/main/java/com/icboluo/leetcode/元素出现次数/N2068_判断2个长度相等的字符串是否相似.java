package com.icboluo.leetcode.元素出现次数;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-22 0:07
 */
class N2068_判断2个长度相等的字符串是否相似 {
    /**
     * 相似条件：每个字符出现频率小于3 FIXME ERROR
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean checkAlmostEquivalent(String word1, String word2) {
        Map<Character, Integer> eleCountMap1 = IntStream.range(0, word1.length())
                .mapToObj(word1::charAt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
        Map<Character, Integer> eleCountMap2 = IntStream.range(0, word2.length())
                .mapToObj(word2::charAt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
        for (int i = 'a'; i <= 'z'; i++) {
            int a = eleCountMap1.getOrDefault((char) i, 0);
            int b = eleCountMap2.getOrDefault((char) i, 0);
            if (Math.abs(a - b) <= 3) {
                return false;
            }
        }
        return true;
    }
}
