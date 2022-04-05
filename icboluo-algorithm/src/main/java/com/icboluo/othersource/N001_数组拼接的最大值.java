package com.icboluo.othersource;

import java.util.*;

/**
 * @author icboluo
 * @since 2021-09-28 13:09
 */
public class N001_数组拼接的最大值 {
    public int maxVal(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            list.add(j);
        }
        list.sort(this::compare);
        Optional<String> reduce = list.stream()
                .map(String::valueOf)
                .reduce((start, cur) -> start + cur);
        return Integer.parseInt(reduce.orElse("0"));
    }

    private int compare(int a, int b) {
        int ab = Integer.parseInt(a + "" + b);
        int ba = Integer.parseInt(b + "" + a);
        return ab > ba ? -1 : 1;
    }
}
