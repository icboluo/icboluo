package com.icboluo.leetcode.消消乐;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-06-05 21:40
 */
class N2465_最大值最小值平均数汇总 {
    // 不同平均值的数量 特殊的解法，并不改变数组本身
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Double> set = new HashSet<>();
        // 取第一个和最后一个
        for (int i = 0; i < nums.length / 2; i++) {
            set.add((nums[i] + nums[nums.length - i - 1]) / 2.0);
        }
        return set.size();
    }
}
