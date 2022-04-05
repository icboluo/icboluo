package com.icboluo.algorithm.回溯算法;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2021-13-12 22:13
 */
public class 全排列 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        var cla = new 全排列();
        LinkedList<Integer> track = new LinkedList<>();
        cla.backtrack(arr, track);
        System.out.println(cla.res);
    }

    private final List<List<Integer>> res = new ArrayList<>();

    /**
     * 回溯算法
     *
     * @param arr   全排列数组
     * @param track 执行路径/追踪
     */
    private void backtrack(int[] arr, LinkedList<Integer> track) {
//        触发结束条件
        if (arr.length == track.size()) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
//            排除不合法选择
            if (track.contains(arr[i])) {
                continue;
            }
//            做选择
            track.add(arr[i]);
//            执行选择/进入下一层决策树
            backtrack(arr, track);
//            撤销选择
            track.removeLast();
        }
    }
}
