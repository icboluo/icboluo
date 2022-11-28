package com.icboluo.leetcode.after_0400;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-26 14:03
 */
class N0434_字符串中的段数 {
    public int countSegments(String s) {
        String[] s1 = s.split(" ");
        return (int) Arrays.stream(s1).filter(str -> !"".equals(str)).count();
    }
}
