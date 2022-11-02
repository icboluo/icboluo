package com.icboluo.leetcode.after_0200;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-11-01 12:50
 */
public class N0350_两个数组的交集 {
    public static void main(String[] args) {
        N0350_两个数组的交集 cla = new N0350_两个数组的交集();
        int[] arr1 = {1, 2, 2, 1, 3};
        int[] arr2 = {2, 2, 3};
        int[] intersect = cla.intersect(arr1, arr2);
        System.out.println(Arrays.toString(intersect));
    }

    /**
     * 用map
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // 存储元素出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int item : nums1) {
            map.merge(item, 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>();
        for (int item : nums2) {
            if (map.containsKey(item)) {
                if (map.get(item) == 1) {
                    map.remove(item);
                } else {
                    map.put(item, map.get(item) - 1);
                }
                list.add(item);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
