package com.icboluo.leetcode.回文数;

/**
 * @author icboluo
 * @since 2022-12-30 0:21
 */
class N0680_删除一个字符是否为回文串 {
    /**
     * 最多删除一个字符 FIXME ERROR
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        boolean haveDel = false;
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            char left = s.charAt(l);
            char right = s.charAt(r);
            if (left == right) {
                l++;
                r--;
            } else {
                if (haveDel) {
                    return false;
                }
                haveDel = true;
                // 如果删一条不够
                if (s.charAt(l + 1) != right && s.charAt(r - 1) != left) {
                    return false;
                }
                if (s.charAt(l + 1) == right) {
                    l++;
                } else if (s.charAt(r - 1) == left) {
                    r--;
                }
            }
        }
        return true;
    }
}
