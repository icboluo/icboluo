package com.icboluo.leetcode.after_0300;

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
        List<Integer> m = cla.m(arr1, arr2);
        System.out.println("m = " + m);
    }

    /**
     * 一个放到set中，另一个一个一个比较
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private List<Integer> m(int[] arr1, int[] arr2) {
        Set<Integer> set = Arrays.stream(arr1).mapToObj(Integer::valueOf).collect(Collectors.toSet());
        HashSet<Integer> res = new HashSet<>();
        for (int item : arr2) {
            if (set.contains(item)) {
                res.add(item);
            }
        }
        return new ArrayList<>(res);
    }
}
