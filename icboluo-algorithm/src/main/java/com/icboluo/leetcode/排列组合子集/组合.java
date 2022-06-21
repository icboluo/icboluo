package com.icboluo.leetcode.排列组合子集;

import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-06-21 19:17
 */
public class 组合 {

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
        backtrack(0, n, k);
        return ans;
    }

    private void backtrack(int start, int n, int k) {
        if (track.size() == k) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i < n; i++) {
            track.add(i + 1);
            backtrack(i + 1, n, k);
            track.removeLast();
        }
    }
}
