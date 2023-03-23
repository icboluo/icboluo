package com.icboluo.leetcode.after_2000;

import java.util.*;

/**
 * @author icboluo
 * @since 2023-03-21 22:42
 */
class N2200_求值为key的附近索引 {
    /**
     * 查找数组中的所有k距离索引 FIXME ERROR
     * 暴力解
     *
     * @param nums
     * @param key
     * @param k
     * @return
     */
    public List<Integer> findKDistantIndices1(int[] nums, int key, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                set.add(i);
                for (int j = 1; j < k; j++) {
                    set.add(i + j);
                    set.add(i - j);
                }
            }
        }
        return set.stream().filter(i -> i >= 0 && i < nums.length).sorted().toList();
    }

    public List<Integer> findKDistantIndices2(int[] nums, int key, int k) {
        List<Integer> list = Arrays.stream(nums).filter(i -> nums[i] == key).boxed().toList();
        // 前面的最大值，也是当前的最小值
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for (Integer mid : list) {
            // 从最大的中找，防止重复
            int i = Math.max(max, mid - k);
            while (i <= mid + k && i < nums.length) {
                res.add(i);
                i++;
            }
            max = i;
        }
        return res;
    }
}
