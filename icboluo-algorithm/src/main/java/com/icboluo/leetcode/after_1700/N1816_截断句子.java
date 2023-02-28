package com.icboluo.leetcode.after_1700;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-02-28 21:54
 */
class N1816_截断句子 {
    /**
     * @param s
     * @param k 单词个数
     * @return
     */
    public String truncateSentence(String s, int k) {
        return Arrays.stream(s.split(" "))
                .limit(k)
                .collect(Collectors.joining(" "));
    }
}
