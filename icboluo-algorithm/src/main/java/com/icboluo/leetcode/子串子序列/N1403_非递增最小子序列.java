package com.icboluo.leetcode.子串子序列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-01-07 22:32
 */
class N1403_非递增最小子序列 {
    /**
     * 子序列和大于剩下的
     * 要求长度最小，然后是总和最大的
     *
     * @param nums
     * @return
     */
    public List<Integer> minSubsequence(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        // 子序列和对顺序是没有要求的，把可能的结果尽可能放到数组的前面比较好操作
        int[] sortArr = IntStream.range(0, nums.length)
                .boxed()
                .sorted((a, b) -> nums[b] - nums[a])
                .mapToInt(Integer::intValue)
                .toArray();
        int temp = 0;
        List<Integer> list = new ArrayList<>();
        for (int idx : sortArr) {
            temp += nums[idx];
            list.add(nums[idx]);
            if (temp * 2 > sum) {
                return list;
            }
        }
        return list;
    }
}
