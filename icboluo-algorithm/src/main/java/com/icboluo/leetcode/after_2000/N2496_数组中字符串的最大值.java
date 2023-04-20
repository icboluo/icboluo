package com.icboluo.leetcode.after_2000;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-21 0:09
 */
class N2496_数组中字符串的最大值 {
    public int maximumValue(String[] strs) {
        int max = 0;
        for (String str : strs) {
            boolean isDigit = IntStream.range(0, str.length()).map(str::charAt).allMatch(Character::isDigit);
            // 纯数字就是值
            if (isDigit) {
                max = Math.max(max, Integer.parseInt(str));
            } else {
                // 要不然就是长度
                max = Math.max(max, str.length());
            }
        }
        return max;
    }
}
