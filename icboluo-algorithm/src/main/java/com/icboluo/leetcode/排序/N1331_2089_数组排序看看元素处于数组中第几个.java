package com.icboluo.leetcode.排序;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-12-28 21:39
 */
class N1331_2089_数组排序看看元素处于数组中第几个 {
    // 超时
    public int[] arrayRankTransform1(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().distinct().sorted().toList();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = list.indexOf(arr[i]) + 1;
        }
        return res;
    }

    /**
     * 使用哈希表，好像时间复杂度都是n，为什么上面的就超时了？TODO 0034
     *
     * @param arr
     * @return
     */
    public int[] arrayRankTransform2(int[] arr) {
        int[] ints = Arrays.copyOf(arr, arr.length);
        Arrays.sort(ints);
        // 元素顺序
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ints.length; i++) {
            map.putIfAbsent(ints[i], map.size() + 1);
        }
        // 用旧的做主循环，遍历结果
        for (int i = 0; i < arr.length; i++) {
            ints[i] = map.get(arr[i]);
        }
        return ints;
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
