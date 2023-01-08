package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2023-01-07 23:58
 */
class N1103_向人们分发糖果 {
    /**
     * 第一个人1个，第二个人2个...直到分完，或者再来一遍直到分完
     *
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] arr = new int[num_people];
        int cur = 1;
        while (candies != 0) {
            for (int i = 0; i < arr.length; i++) {
                if (candies > cur) {
                    arr[i] += cur;
                    candies -= cur;
                } else {
                    arr[i] += candies;
                    candies = 0;
                    break;
                }
                cur++;
            }
        }
        return arr;
    }
}
