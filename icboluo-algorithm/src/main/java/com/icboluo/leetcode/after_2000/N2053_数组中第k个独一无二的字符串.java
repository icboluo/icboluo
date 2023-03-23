package com.icboluo.leetcode.after_2000;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-03-16 22:49
 */
class N2053_数组中第k个独一无二的字符串 {
    // 数组中的第 K 个不同的字符串
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> eleCountMap = Arrays.stream(arr)
                .collect(Collectors.toMap(Function.identity(), ele -> 1, Integer::sum));
        return Arrays.stream(arr).filter(item -> eleCountMap.get(item) == 1).skip(k - 1).findFirst().orElse("");
    }
}
