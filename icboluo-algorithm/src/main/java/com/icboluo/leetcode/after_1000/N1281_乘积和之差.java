package com.icboluo.leetcode.after_1000;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-12-28 21:53
 */
 class N1281_乘积和之差 {
    public int subtractProductAndSum(int n) {
        String str = n + "";
        int multiply = IntStream.range(0, str.length())
                .map(i -> Integer.parseInt(String.valueOf(str.charAt(i))))
                .reduce(1, (a, b) -> a * b);
        int sum = IntStream.range(0, str.length()).map(i -> Integer.parseInt(String.valueOf(str.charAt(i)))).sum();
        return multiply - sum;
    }
}
