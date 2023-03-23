package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-03-23 22:23
 */
class N2027_转换字符串的最少操作数 {
    /**
     * 一次修改3个连续字符为O，求全部为O的最少操作次数
     *
     * @param s
     * @return
     */
    public int minimumMoves(String s) {
        char[] arr = s.toCharArray();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 'O') {
                arr[i] = 'O';
                if (i + 1 < arr.length) {
                    arr[i + 1] = 'O';
                }
                if (i + 2 < arr.length) {
                    arr[i + 2] = 'O';
                }
                count++;
            }
        }
        return count;
    }
}
