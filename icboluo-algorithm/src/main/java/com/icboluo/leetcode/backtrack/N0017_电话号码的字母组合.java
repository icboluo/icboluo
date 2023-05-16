package com.icboluo.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-05-16 21:18
 */
class N0017_电话号码的字母组合 {
    public static void main(String[] args) {
        var cla = new N0017_电话号码的字母组合();
        System.out.println(cla.letterCombinations(""));
    }

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<List<String>> res;

    LinkedList<Character> track;

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<>();
        }
        res = new LinkedList<>();
        track = new LinkedList<>();
        combination(0, digits);
        return res.stream().map(li -> String.join("", li)).toList();
    }

    /**
     * 可重复定长组合问题，回溯算法好难理解
     *
     * @param start
     * @param digits
     */
    private void combination(int start, String digits) {
        if (track.size() == digits.length()) {
            res.add(track.stream().map(String::valueOf).toList());
            return;
        }
        // 长度越界，这里只是为了修正结果
        if (start == digits.length()) {
            return;
        }
        String matchWord = KEYS[digits.charAt(start) - '0'];
        // 外层的循环代表每个字符需要使用1次
        for (int j = 0; j < matchWord.length(); j++) {
            for (int i = start; i < digits.length(); i++) {
                track.add(matchWord.charAt(j));
                combination(i + 1, digits);
                track.removeLast();
            }
        }
    }
}
