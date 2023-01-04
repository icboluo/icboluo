package com.icboluo.leetcode.最长回文串;

/**
 * @author icboluo
 * @since 2022-12-30 0:21
 */
class N0680_删除一个字符是否为回文串 {

    /**
     * 最多删除一个字符 ERROR
     *
     * @param s
     * @return
     */
    public boolean validPalindrome1(String s) {
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
                // 这样的写法是有缺陷的，我们不确定应该删除的是左边的元素还是右边的元素，仅仅单词的判断下一个元素是不足的
                if (s.charAt(l + 1) == right) {
                    l++;
                } else if (s.charAt(r - 1) == left) {
                    r--;
                }
            }
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                // 舍弃左右的任意一个元素
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            }
            l++;
            r--;
        }
        return true;
    }


    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
