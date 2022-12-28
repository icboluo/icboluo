package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-12-28 21:39
 */
class N1331_2089_数组排序看看元素处于数组中第几个 {
    // TODO 超时
    public int[] arrayRankTransform(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().distinct().sorted().toList();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = list.indexOf(arr[i]) + 1;
        }
        return res;
    }

    /**
     * 2089 数字在数组中的索引，数组需要排序
     *
     * @param nums
     * @param target
     * @return
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        return IntStream.range(0, nums.length).filter(i -> nums[i] == target).boxed().toList();
    }
}
