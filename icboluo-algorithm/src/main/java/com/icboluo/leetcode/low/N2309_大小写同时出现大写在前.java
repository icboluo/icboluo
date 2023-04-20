package com.icboluo.leetcode.low;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-20 22:20
 */
class N2309_大小写同时出现大写在前 {
    // 大小写同时出现；字符串中第一个出现的大写字母返回
    public String greatestLetter(String s) {
        List<Character> list = IntStream.range(0, s.length()).mapToObj(s::charAt).toList();
        for (char i = 'Z'; i >= 'A'; i--) {
            if (list.contains(i) && list.contains((char) (i + 'a' - 'A'))) {
                return i + "";
            }
        }
        return "";
    }
}
