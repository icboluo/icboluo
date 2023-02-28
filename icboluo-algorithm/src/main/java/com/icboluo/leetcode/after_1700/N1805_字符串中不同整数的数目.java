package com.icboluo.leetcode.after_1700;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-02-28 21:43
 */
class N1805_字符串中不同整数的数目 {
    public int numDifferentIntegers(String word) {
        List<String> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                temp += word.charAt(i);
            } else {
                list.add(temp);
                temp = "";
            }
        }
        list.add(temp);
        return (int) list.stream()
                .filter(str -> !str.isEmpty())
                .map(str -> {
                    if (str.length() == 1 || str.charAt(0) != '0') {
                        return str;
                    }
                    // 去除前置0
                    while (str.length() > 1 && str.charAt(0) == '0') {
                        str = str.substring(1);
                    }
                    return str;
                })
                .distinct()
                .count();
    }
}
