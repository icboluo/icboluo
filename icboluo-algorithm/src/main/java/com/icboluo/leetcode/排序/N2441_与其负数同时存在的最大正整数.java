package com.icboluo.leetcode.排序;

import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-04-20 23:26
 */
class N2441_与其负数同时存在的最大正整数 {
    /**
     * 最大的相反数
     *
     * @param nums
     * @return
     */
    public int findMaxK(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted().toList();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.contains(-list.get(i))) {
                return list.get(i);
            }
        }
        return -1;
    }
}
