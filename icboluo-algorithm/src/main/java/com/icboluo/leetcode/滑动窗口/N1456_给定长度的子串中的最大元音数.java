package com.icboluo.leetcode.滑动窗口;

import com.icboluo.constant.CharConstant;

/**
 * @author icboluo
 * @since 2023-01-15 16:53
 */
class N1456_给定长度的子串中的最大元音数 {
    public static void main(String[] args) {
        var cla = new N1456_给定长度的子串中的最大元音数();
        System.out.println(cla.minimumRecolors("WBW", 2));
    }

    /**
     * 长度为k的子串，中元音字母数量最大是多少
     *
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        int l = 0;
        int r = 0;
        int max = 0;
        int winCount = 0;
        while (r < s.length()) {
            char right = s.charAt(r++);
            if (CharConstant.VOWELS_LIST.contains(right)) {
                winCount++;
            }
            max = Math.max(max, winCount);
            if (r - l == k) {
                if (CharConstant.VOWELS_LIST.contains(s.charAt(l++))) {
                    winCount--;
                }
            }
        }
        return max;
    }

    /**
     * 2379 获取k个连续黑色块的最少重新着色
     * 可以将白色染成黑色，返回最少操作数
     *
     * @param blocks
     * @param k
     * @return
     */
    public int minimumRecolors(String blocks, int k) {
        char[] arr = blocks.toCharArray();
        int l = 0;
        int r = 0;
        int winWrite = 0;
        int min = k;
        while (r < arr.length) {
            char right = arr[r++];
            if (right == 'W') {
                winWrite++;
            }
            // 这里区间最小为2啊，怎么求1的
            if (r - l == k) {
                char left = arr[l++];
                min = Math.min(min, winWrite);
                if (left == 'W') {
                    winWrite--;
                }
            }
        }
        return min;
    }
}
