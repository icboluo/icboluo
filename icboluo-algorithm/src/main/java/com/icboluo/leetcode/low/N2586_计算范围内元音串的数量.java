package com.icboluo.leetcode.low;

import java.util.Arrays;

import static com.icboluo.constant.CharConstant.VOWELS_LIST;

/**
 * @author icboluo
 * @since 2023-03-30 21:52
 */
class N2586_计算范围内元音串的数量 {
    // 如果是元音字符开始&元音字符结尾，++
    public int vowelStrings(String[] words, int left, int right) {
        return (int) Arrays.stream(words, left, right + 1)
                .filter(str -> VOWELS_LIST.contains(str.charAt(0)) && VOWELS_LIST.contains(
                        str.charAt(str.length() - 1)))
                .count();
    }
}
