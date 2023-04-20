package com.icboluo.leetcode.元素出现次数;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-20 22:08
 */
class N2287_重新排列字符生成目标字符串 {
    public static void main(String[] args) {
        var cla = new N2287_重新排列字符生成目标字符串();
        System.out.println(cla.rearrangeCharacters("ilovecodingonleetcode", "code"));
    }

    // 重新排列字符串以生成目标字符串(每个字符只能使用一次
    public int rearrangeCharacters(String s, String target) {
        Map<Character, Integer> eleCountMap1 = IntStream.range(0, s.length())
                .mapToObj(s::charAt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
        Map<Character, Integer> eleCountMap2 = IntStream.range(0, target.length())
                .mapToObj(target::charAt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : eleCountMap2.entrySet()) {
            Character key = entry.getKey();
            min = Math.min(min, eleCountMap1.getOrDefault(key, 0) / entry.getValue());
        }
        return min;
    }
}
