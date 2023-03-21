package com.icboluo.leetcode.low;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-21 23:49
 */
class N2114_句子找到最大的单词数 {
    public int mostWordsFound(String[] sentences) {
        return Arrays.stream(sentences).map(str -> str.split(" ")).mapToInt(arr -> arr.length).max().orElse(0);
    }
}
