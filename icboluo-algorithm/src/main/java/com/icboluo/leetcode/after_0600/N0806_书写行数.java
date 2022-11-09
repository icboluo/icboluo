package com.icboluo.leetcode.after_0600;

/**
 * @author icboluo
 * @since 2022-11-07 22:51
 */
class N0806_书写行数 {
    // todo error
    public int[] numberOfLines(int[] widths, String s) {
        int total = 1;
        int tempWidth = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int width = widths[ch - 'a'];
            if (tempWidth + width > 100) {
                total++;
                // 这里要换行写一个完整的单词，而不是先把上一行写完
                tempWidth = width;
            } else {
                tempWidth += width;
            }
        }
        return new int[]{total, tempWidth};
    }
}
