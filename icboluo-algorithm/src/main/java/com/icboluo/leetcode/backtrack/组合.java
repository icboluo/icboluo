package com.icboluo.leetcode.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-06-21 19:17
 */
class 组合 {

    List<List<Integer>> ans;
    LinkedList<Integer> track;

    /**
     * N0077 定长组合
     *
     * @param n 元素个数，代表数组
     * @param k 目标长度
     * @return 组合情况
     */
    List<List<Integer>> combine(int n, int k) {
        ans = new LinkedList<>();
        track = new LinkedList<>();
        backtrack1(0, n, k);
        return ans;
    }

    private void backtrack1(int start, int n, int k) {
        if (track.size() == k) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i < n; i++) {
            track.add(i + 1);
            backtrack1(i + 1, n, k);
            track.removeLast();
        }
    }


    int trackSum = 0;

    /**
     * N0040 和为target的组合（不可重复选择
     *
     * @param candidates 候选人 {10,1,2,7,6,1,5}
     * @param target     8
     * @return [1, 1, 6], [1,2,5], [1,7], [2,6]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new LinkedList<>();
        track = new LinkedList<>();
        trackSum = 0;
        if (candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);
        backtrack2(candidates, 0, target);
        return ans;
    }

    private void backtrack2(int[] arr, int start, int target) {
        if (trackSum == target) {
            ans.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        // 这里第一次遍历的索引是start，不是0，如果使用0就会出现重复的结果，即便最后使用set去重，也会有效率问题，禁止组合问题这样写
        for (int i = start; i < arr.length; i++) {
            if (i > start && arr[i] == arr[i - 1]) {
                continue;
            }
            track.add(arr[i]);
            trackSum += arr[i];
            backtrack2(arr, i + 1, target);
            track.removeLast();
            trackSum -= arr[i];
        }
    }

    /**
     * N0039 和为target的组合（可重复选择
     *
     * @param candidates [1,2,3]
     * @param target     3
     * @return [ [1,1,1],[1,2],[3] ]
     */
    List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new LinkedList<>();
        track = new LinkedList<>();
        trackSum = 0;
        if (candidates.length == 0) {
            return ans;
        }
        backtrack3(candidates, 0, target);
        return ans;
    }

    private void backtrack3(int[] arr, int start, int target) {
        if (trackSum == target) {
            ans.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < arr.length; i++) {
            track.add(arr[i]);
            trackSum += arr[i];
            backtrack3(arr, i, target);
            track.removeLast();
            trackSum -= arr[i];
        }
    }
}
