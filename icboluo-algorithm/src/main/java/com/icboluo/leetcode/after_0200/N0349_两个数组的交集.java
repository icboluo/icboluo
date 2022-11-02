package com.icboluo.leetcode.after_0200;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-11-01 12:46
 */
class N0349_两个数组的交集 {
    public static void main(String[] args) {
        N0349_两个数组的交集 cla = new N0349_两个数组的交集();
        int[] arr1 = {1, 2, 2, 1, 3};
        int[] arr2 = {2, 2, 3};
        int[] intersection = cla.intersection(arr1, arr2);
        System.out.println(Arrays.toString(intersection));
    }

    /**
     * 一个放到set中，另一个一个一个比较
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        HashSet<Integer> res = new HashSet<>();
        for (int item : nums2) {
            if (set.contains(item)) {
                res.add(item);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
