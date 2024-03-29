package com.icboluo.leetcode.after_0200;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-09-20 0:44
 */
class N0241_不同计算结果 {
    Map<String, List<Integer>> cache = new HashMap<>();

    List<Integer> diff(String input) {
        if (cache.containsKey(input)) {
            return cache.get(input);
        }
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '-' || ch == '*' || ch == '+') {
                // 归并排序
                List<Integer> left = diff(input.substring(0, i));
                List<Integer> right = diff(input.substring(i + 1));
                for (Integer l : left) {
                    for (Integer r : right) {
                        ans.add(cal(l, r, ch));
                    }
                }
            }
        }
        if (ans.isEmpty()) {
            ans.add(Integer.parseInt(input));
        }
        cache.put(input, ans);
        return ans;
    }

    private Integer cal(Integer l, Integer r, char ch) {
        return switch (ch) {
            case '+' -> l + r;
            case '-' -> l - r;
            case '*' -> l * r;
            default -> -1;
        };
    }
}
