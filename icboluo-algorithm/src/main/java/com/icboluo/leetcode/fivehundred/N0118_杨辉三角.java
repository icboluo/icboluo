package com.icboluo.leetcode.fivehundred;

import java.util.ArrayList;
import java.util.List;

class N0118_杨辉三角 {
    public static void main(String[] args) {
        m(8);
    }

    private static void m(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        ans.add(list0);
        for (int i = 1; i < n; i++) {
            List<Integer> listi = new ArrayList<>();
            List<Integer> pre = ans.get(i - 1);
            listi.add(1);
            for (int j = 1; j < i; j++) {
                listi.add(pre.get(j - 1) + pre.get(j));
            }
            listi.add(1);
            ans.add(listi);
        }
        ans.forEach(System.out::println);
    }
}
