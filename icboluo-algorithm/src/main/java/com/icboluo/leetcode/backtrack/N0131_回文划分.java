package com.icboluo.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-05-16 21:17
 */
class N0131_回文划分 {
    List<List<String>> res;

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        res = new ArrayList<>();
        backtrack(s, new LinkedList<>());
        return res;
    }

    private void backtrack(String s, LinkedList<String> track) {
        if (s == null || s.length() == 0) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(0, i + 1);
            if (!isHuiWen(temp)) {
                continue;
            }
            track.add(temp);
            backtrack(s.substring(i + 1), track);
            track.removeLast();
        }
    }

    private boolean isHuiWen(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
