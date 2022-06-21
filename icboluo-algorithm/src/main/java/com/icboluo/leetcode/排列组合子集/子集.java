package com.icboluo.leetcode.排列组合子集;

import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-06-21 19:10
 */
public class 子集 {
    List<List<Integer>> ans;
    LinkedList<Integer> track;

    /**
     * N0078 求数组元素的子排列
     *
     * @param nums 数组元素
     * @return 子排列
     */
    List<List<Integer>> subsets(int[] nums) {
        ans = new LinkedList<>();
        track = new LinkedList<>();
        backtrack(nums, 0);
        return ans;
    }

    private void backtrack(int[] nums, int start) {
        ans.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        子集 cla = new 子集();
        List<List<Integer>> subsets = cla.subsets(new int[]{1, 2, 3});
        System.out.println("subsets = " + subsets);
    }
}
