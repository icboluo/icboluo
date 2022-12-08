package com.icboluo.leetcode.括号匹配深度;

/**
 * @author icboluo
 * @since 2022-12-08 23:24
 */
class N1021_删除最外括号 {
    public String removeOuterParentheses(String s) {
        String res = "";
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' && left++ > 0) {
                res += "(";
            }
            if (s.charAt(i) == ')' && --left > 0) {
                res += ")";
            }
        }
        return res;
    }
}
