package com.icboluo.algorithm.回溯算法;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * todo
 *
 * @author icboluo
 * @date 2021-25-12 22:25
 */
public class 组合 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        var cla = new 组合();
        LinkedList<Integer> track = new LinkedList<>();
        cla.backtrack(arr, track);
        System.out.println(cla.res);
    }

    private final List<List<Integer>> res = new ArrayList<>();

    private void backtrack(int[] arr, LinkedList<Integer> track) {

    }
}
