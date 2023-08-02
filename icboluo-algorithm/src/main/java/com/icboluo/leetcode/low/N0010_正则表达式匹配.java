package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-08-02 20:37
 */
class N0010_正则表达式匹配 {
    public static void main(String[] args) {
        var cla = new N0010_正则表达式匹配();
        // FIXME
        System.out.println(cla.isMatch("abc", "a***abc"));
    }

    public boolean isMatch(String s, String p) {
        return s.matches(p);
    }
}
