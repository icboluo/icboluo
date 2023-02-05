package com.icboluo.leetcode.after_1300;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-02-05 13:32
 */
public class N1592_重新排列空格 {
    public String reorderSpaces(String text) {
        String[] arr = Arrays.stream(text.split(" ")).filter(s -> !s.isBlank()).toArray(String[]::new);
        int spaceLen = text.length() - Arrays.stream(arr).mapToInt(String::length).sum();
        // 特殊情况处理
        if (arr.length == 1) {
            return arr[0] + " ".repeat(spaceLen);
        }
        int oneSpaceLen = spaceLen / (arr.length - 1);
        String space = " ".repeat(oneSpaceLen);
        String res = String.join(space, arr);
        res += " ".repeat(spaceLen % (arr.length - 1));
        return res;
    }

    // TODO 68 it`s hard,but it`s good problem
}
