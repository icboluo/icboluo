package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-02-28 20:59
 */
class N1700_无法吃午餐的学生数量 {

    /**
     * 标准解法，注意题意：题目中说明studets可以旋转，但是sandwiches不能，说明students的顺序并不重要，而sandwiches的顺序是有用的，需要来计算结果
     *
     * @param students   学生和午餐相同则可以吃饭
     * @param sandwiches
     * @return
     */
    public int countStudents(int[] students, int[] sandwiches) {
        int zeroStudent = 0;
        int oneStudent = 0;
        for (int student : students) {
            if (student == 0) {
                zeroStudent++;
            } else {
                oneStudent++;
            }
        }
        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (zeroStudent == 0) {
                    return oneStudent;
                }
                zeroStudent--;
            }
            if (sandwich == 1) {
                if (oneStudent == 0) {
                    return zeroStudent;
                }
                oneStudent--;
            }
        }
        return 0;
    }

    /**
     * 2073  购票所需的时间
     * 1人一张票，买完去后面排队，循环
     *
     * @param tickets 每个人需要买的票数
     * @param k       k个人完成买票花费的最少时间
     * @return
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int sum = 0;
        for (int i = 0; i < tickets.length; i++) {
            // 对于k索引之前的人，必须经过较多个
            if (i <= k) {
                sum += Math.min(tickets[i], tickets[k]);
            } else {
                // 对于k后面的人，可以少买一张票
                sum += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        return sum;
    }
}
