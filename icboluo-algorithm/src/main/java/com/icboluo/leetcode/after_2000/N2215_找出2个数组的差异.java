package com.icboluo.leetcode.after_2000;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-11-22 12:22
 */
class N2215_找出2个数组的差异 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set11 = new HashSet<>();
        Set<Integer> set22 = new HashSet<>();
        for (int i : nums1) {
            if (!set2.contains(i)) {
                set11.add(i);
            }

        }
        for (int i : nums2) {
            if (!set1.contains(i)) {
                set22.add(i);
            }
        }
        res.add(new ArrayList<>(set11));
        res.add(new ArrayList<>(set22));
        return res;
    }
}
