package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-01-07 16:33
 */
class N1460_反转数组使2个数组相等 {
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Long> arr1 = Arrays.stream(target).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        Map<Integer, Long> arr2 = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        return arr1.equals(arr2);
    }
}
