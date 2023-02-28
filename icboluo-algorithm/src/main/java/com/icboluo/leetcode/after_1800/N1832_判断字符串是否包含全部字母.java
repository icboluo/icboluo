package com.icboluo.leetcode.after_1800;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-02-28 21:56
 */
class N1832_判断字符串是否包含全部字母 {
    // 方法1：字母排序，判断字符串是否包含每一个
    public boolean checkIfPangram1(String sentence) {
        for (char i = 'a'; i <= 'z'; i++) {
            if (sentence.indexOf(i) == -1) {
                return false;
            }
        }
        return true;
    }

    // 方法2：去重求个数
    public boolean checkIfPangram2(String sentence) {
        return IntStream.range(0, sentence.length())
                .map(sentence::charAt)
                .distinct()
                .count() == 26;
    }
}
