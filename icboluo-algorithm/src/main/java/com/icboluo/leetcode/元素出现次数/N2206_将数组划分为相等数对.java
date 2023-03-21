package com.icboluo.leetcode.元素出现次数;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-03-21 22:36
 */
class N2206_将数组划分为相等数对 {
    /**
     * 将数组分成len/2，每组元素相等
     *
     * @param nums
     * @return
     */
    public boolean divideArray(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)))
                .values()
                .stream()
                .allMatch(val -> val % 2 == 0);
    }
}
