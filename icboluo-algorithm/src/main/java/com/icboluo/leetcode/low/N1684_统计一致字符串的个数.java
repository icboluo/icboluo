package com.icboluo.leetcode.low;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-29 23:30
 */
class N1684_统计一致字符串的个数 {
    /**
     * 单词 包含 数组中 的个数 FIXME ERROR
     *
     * @param allowed
     * @param words
     * @return
     */
    public int countConsistentStrings(String allowed, String[] words) {
        return (int) Arrays.stream(words)
                .filter(word -> IntStream.range(0, word.length())
                        .mapToObj(word::charAt)
                        .allMatch(ch -> allowed.contains(String.valueOf(ch))))
                .count();
    }
}
