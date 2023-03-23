package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-23 23:29
 */
class N1957_删除字符使子串相邻不连续 {
    /**
     * 删除一些字符，使子串最长相等小于3
     *
     * @param s
     * @return
     */
    public String makeFancyString(String s) {
        StringBuilder res = new StringBuilder();
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
            } else {
                count = 1;
            }
            if (count < 3) {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}
