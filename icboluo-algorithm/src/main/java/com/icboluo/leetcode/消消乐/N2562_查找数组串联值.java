package com.icboluo.leetcode.消消乐;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-21 0:55
 */
class N2562_查找数组串联值 {
    /**
     * 首位末尾拼接，再求和
     *
     * @param nums
     * @return
     */
    public long findTheArrayConcVal(int[] nums) {
        long sum = IntStream.range(0, nums.length / 2)
                .mapToObj(i -> nums[i] + "" + nums[nums.length - i - 1])
                .mapToLong(Long::parseLong)
                .sum();
        if (nums.length % 2 == 0) {
            return sum;
        }
        return sum + nums[nums.length / 2];
    }
}
