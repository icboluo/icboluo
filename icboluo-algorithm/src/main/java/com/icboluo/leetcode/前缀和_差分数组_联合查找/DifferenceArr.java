package com.icboluo.leetcode.前缀和_差分数组_联合查找;

public class DifferenceArr {
    int[] diff;

    public DifferenceArr(int[] arr) {
        diff = new int[arr.length];
        diff[0] = arr[0];
        for (int i = 1; i < diff.length; i++) {
            diff[i] = arr[i] - arr[i - 1];
        }
    }

    public int[] restore() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    public void increment(int i, int j, int val) {
        diff[i] += val;
        // 当增加的区间到最后的时候，就不用再减少了
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }
}
