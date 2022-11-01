package com.icboluo.leetcode.after_0000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-01 12:50
 */
public class N0350_两个数组的交集 {
    public static void main(String[] args) {
        N0350_两个数组的交集 cla = new N0350_两个数组的交集();
        int[] arr1 = {1, 2, 2, 1, 3};
        int[] arr2 = {2, 2, 3};
        List<Integer> m = cla.m(arr1, arr2);
        System.out.println("m = " + m);
    }

    /**
     * 用map
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private List<Integer> m(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int item : arr1) {
            map.merge(item, 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>();
        for (int item : arr2) {
            if (map.containsKey(item)) {
                if (map.get(item) == 1) {
                    map.remove(item);
                } else {
                    map.put(item, map.get(item) - 1);
                }
                list.add(item);
            }
        }
        return list;
    }
}
