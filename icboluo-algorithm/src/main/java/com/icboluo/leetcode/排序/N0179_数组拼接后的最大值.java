package com.icboluo.leetcode.排序;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author icboluo
 * @since 2023-06-05 21:46
 */
public class N0179_数组拼接后的最大值 {
    public static void main(String[] args) {
        var cla = new N0179_数组拼接后的最大值();
        System.out.println(cla.largestNumber(new int[]{1000000000, 1000000000}));
    }

    public String largestNumber(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            list.add(j);
        }
        list.sort(this::compare);
        Optional<String> reduce = list.stream().map(String::valueOf).reduce((start, cur) -> start + cur);
        // 防止00的情况发生
        return reduce.map(str -> {
            while (str.length() > 1 && str.charAt(0) == '0') {
                str = str.substring(1);
            }
            return str;
        }).orElse("0");
    }

    private int compare(int a, int b) {
        BigInteger ab = new BigInteger(a + "" + b);
        BigInteger ba = new BigInteger(b + "" + a);
        return ab.compareTo(ba) > 0 ? -1 : 1;
    }
}
