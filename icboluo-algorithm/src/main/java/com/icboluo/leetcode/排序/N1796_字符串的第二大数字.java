package com.icboluo.leetcode.排序;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-02-28 22:17
 */
class N1796_字符串的第二大数字 {
    public int secondHighest(String s) {
        return IntStream.range(0, s.length())
                .mapToObj(s::charAt)
                .filter(Character::isDigit)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .map(ch -> Character.digit(ch, 10))
                .findFirst().orElse(-1);
    }
}
