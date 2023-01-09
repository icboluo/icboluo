package com.icboluo.leetcode.after_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-08 13:37
 */
class N1417_重新格式化字符串 {
    /**
     * 数字和字母挨着放
     *
     * @param s
     * @return
     */
    public String reformat(String s) {
        List<Character> numList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                numList.add(s.charAt(i));
            } else {
                charList.add(s.charAt(i));
            }
        }
        if (Math.abs(numList.size() - charList.size()) > 1) {
            return "";
        }
        if (numList.size() > charList.size()) {
            return join(numList, charList);
        } else {
            return join(charList, numList);
        }
    }

    private String join(List<Character> maxList, List<Character> minList) {
        String res = "";
        for (int i = 0; i < maxList.size(); i++) {
            res += maxList.get(i);
            if (minList.size() > i) {
                res += minList.get(i);
            }
        }
        return res;
    }
}
