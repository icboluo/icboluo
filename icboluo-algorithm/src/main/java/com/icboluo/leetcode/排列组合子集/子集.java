package com.icboluo.leetcode.排列组合子集;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-06-21 19:10
 */
class 子集 {
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
        backtrack1(nums, 0);
        return ans;
    }

    private void backtrack1(int[] nums, int start) {
        ans.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack1(nums, i + 1);
            track.removeLast();
        }
    }

    /**
     * N0090 元素可重复不可复选
     *
     * @param nums {1,2,2}
     * @return [ [],[1],[2],[1,2],[2,2],[1,2,2] ]
     */
    List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new LinkedList<>();
        track = new LinkedList<>();

        Arrays.sort(nums);
        backtrack2(nums, 0);
        return ans;
    }

    private void backtrack2(int[] arr, int start) {
        ans.add(new LinkedList<>(track));
        for (int i = start; i < arr.length; i++) {
            if (i > start && arr[i] == arr[i - 1]) {
                continue;
            }
            track.add(arr[i]);
            backtrack2(arr, i + 1);
            track.removeLast();
        }
    }


    public static void main(String[] args) {
        子集 cla = new 子集();
        List<List<Integer>> subsets = cla.subsets(new int[]{1, 2, 3});
        System.out.println("subsets = " + subsets);
    }
}
