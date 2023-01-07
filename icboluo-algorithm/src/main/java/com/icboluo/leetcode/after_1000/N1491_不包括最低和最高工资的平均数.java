package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-01-07 16:13
 */
class N1491_不包括最低和最高工资的平均数 {
    public double average(int[] salary) {
        Arrays.sort(salary);
        return IntStream.range(1, salary.length - 1).map(i -> salary[i]).average().getAsDouble();
    }
}
