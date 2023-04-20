package com.icboluo.leetcode.after_2000;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-20 23:34
 */
class N2451_奇数串差 {
    /**
     * 字符间距离相等 FIXME ERROR
     *
     * @param words
     * @return
     */
    public String oddString(String[] words) {
        for (String word : words) {
            long count = IntStream.range(0, word.length() - 1)
                    .map(i -> word.charAt(i + 1) - word.charAt(i))
                    .distinct()
                    .count();
            if (count == 1) {
                return word;
            }
        }
        return "";
    }
}
