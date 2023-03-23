package com.icboluo.leetcode.after_2000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-23 22:10
 */
class N2032_至少2个数组中存在的值 {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        ArrayList<Integer> list = new ArrayList<>();
        jiaoJi(nums1, nums2, list);
        jiaoJi(nums1, nums3, list);
        jiaoJi(nums2, nums3, list);
        return list;
    }

    private void jiaoJi(int[] arr1, int[] arr2, ArrayList<Integer> list) {
        for (int i : arr1) {
            if (list.contains(i)) {
                continue;
            }
            for (int j : arr2) {
                if (i == j && !list.contains(i)) {
                    list.add(i);
                }
            }
        }
    }
}
