package com.icboluo.leetcode.low;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-23 23:13
 */
class N2011_执行操作后变量的最终值 {
    public int finalValueAfterOperations(String[] operations) {
        return Arrays.stream(operations).mapToInt(str -> str.contains("++") ? 1 : -1).sum();
    }
}
