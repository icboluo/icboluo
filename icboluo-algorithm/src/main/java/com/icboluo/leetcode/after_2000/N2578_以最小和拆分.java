package com.icboluo.leetcode.after_2000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-06-12 0:46
 */
class N2578_以最小和拆分 {
    // 以最小和拆分 数字拆分问题
    // 我们需要将非0的数平均的分成2份即可
    public static int splitNum(int num) {
        List<Integer> ele = new ArrayList<>();
        while (num > 0) {
            ele.add(num % 10);
            num /= 10;
        }
        List<Integer> list = ele.stream().filter(i -> i != 0).sorted().toList();
        int a = 0;
        int b = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                a = a * 10 + list.get(i);
            } else {
                b = b * 10 + list.get(i);
            }
        }
        return a + b;
    }
}
