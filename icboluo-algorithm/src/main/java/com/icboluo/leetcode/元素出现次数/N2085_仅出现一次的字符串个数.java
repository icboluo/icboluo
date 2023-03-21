package com.icboluo.leetcode.元素出现次数;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-03-21 23:57
 */
class N2085_仅出现一次的字符串个数 {
    /**
     * 返回在每一个数组中，仅出现一次的字符串个数
     *
     * @param words1
     * @param words2
     * @return
     */
    public int countWords(String[] words1, String[] words2) {
        List<String> list1 = Arrays.stream(words1)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<String> list2 = Arrays.stream(words2)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();

        // 求交集 TODO 349 881 2053
        list1.retainAll(list2);
        return list1.size();
    }
}
