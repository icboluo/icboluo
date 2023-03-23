package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-24 0:15
 */
class N1784_是否前面是1后面是0 {
    public boolean checkOnesSegment1(String s) {
        return !s.contains("01");
    }

    public boolean checkOnesSegment2(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0' && s.charAt(i + 1) == '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * N1869 判断 1组成的连续子串大于0组成的子串
     *
     * @param s
     * @return
     */
    public boolean checkZeroOnes(String s) {
        int max0 = 0;
        int max1 = 0;
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            // 这块索引有点难确定
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (s.charAt(i) == '0') {
                    max0 = Math.max(max0, i - pre);
                }else{
                    max1 = Math.max(max1, i - pre);
                }
                pre = i;
            }
        }
        return max1 > max0;
    }
}
