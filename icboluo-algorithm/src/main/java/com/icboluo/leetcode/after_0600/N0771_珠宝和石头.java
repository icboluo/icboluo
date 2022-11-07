package com.icboluo.leetcode.after_0600;

/**
 * @author icboluo
 * @since 2022-11-07 23:15
 */
class N0771_珠宝和石头 {
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            char ch = stones.charAt(i);
            if (jewels.indexOf(ch) != -1) {
                count++;
            }
        }
        return count;
    }
}
