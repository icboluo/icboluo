package com.icboluo.leetcode.排序;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author icboluo
 * @since 2023-06-05 21:46
 */
public class N0179_数组拼接后的最大值 {
    // FIXME ERROR
    public String largestNumber(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            list.add(j);
        }
        list.sort(this::compare);
        Optional<String> reduce = list.stream().map(String::valueOf).reduce((start, cur) -> start + cur);
        return reduce.orElse("0");
    }

    private int compare(int a, int b) {
        long ab = Long.parseLong(a + "" + b);
        long ba = Long.parseLong(b + "" + a);
        return ab > ba ? -1 : 1;
    }
}
