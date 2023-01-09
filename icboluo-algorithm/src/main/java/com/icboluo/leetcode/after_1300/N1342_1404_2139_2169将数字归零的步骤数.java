package com.icboluo.leetcode.after_1300;

/**
 * @author icboluo
 * @since 2023-01-09 22:04
 */
class N1342_1404_2139_2169将数字归零的步骤数 {
    /**
     * 1342 奇数移动2次，偶数移动1次
     *
     * @param num
     * @return
     */
    public int numberOfSteps(int num) {
        // 特殊情况处理
        if (num == 0) {
            return 0;
        }
        int count = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                count++;
            } else {
                count += 2;
            }
            num = num >> 1;
        }
        return count - 1;
    }

    /**
     * 1404 将二进制表示中的数字减少为1的步骤数：偶数除2，奇数+1
     *
     * @param s
     * @return
     */
    public int numSteps(String s) {
        int count = 0;
        int weiShu = 0;
        // 大于5是为了当 s 足够大的时候，数字格式化不会出现异常，随便写了个数据
        while (s.length() > 5 || Integer.parseInt(s) + weiShu != 1) {
            if (Integer.parseInt(s.charAt(s.length() - 1) + "") + weiShu == 0) {
                weiShu = 0;
                count++;
                s = s.substring(0, s.length() - 1);
            } else if (Integer.parseInt(s.charAt(s.length() - 1) + "") + weiShu == 1) {
                weiShu = 1;
                count += 2;
                s = s.substring(0, s.length() - 1);
            } else {
                // 11   +  1 ---10
                // 111  +  1 ---100
                // 101  +  1 ---11
                if (s.length() == 1 || s.charAt(s.length() - 2) == '0') {
                    weiShu = 1;
                    s = s.length() == 1 ? "0" : s.substring(0, s.length() - 1);
                } else {
                    weiShu = 1;
                    s = s.substring(0, s.length() - 1);
                }
                count++;
            }
        }
        return count;
    }

    /**
     * 2139 达到目标分数的最少步数，只能加1或者加倍
     * 因为限制了最大加倍的次数，所以尽可能的使当前数够大之后再加倍 FIXME ERROR
     *
     * @param target
     * @param maxDoubles
     * @return
     */
    public int minMoves(int target, int maxDoubles) {
        int count = 0;
        while (target != 1) {
            if (target % 2 == 0 && maxDoubles > 0) {
                target = target >> 1;
                maxDoubles--;
            } else {
                // 不要一个一个减，太慢了
                target--;
            }
            count++;
        }
        return count;
    }

    /**
     * 2169 计数操作以获得零，互减操作
     *
     * @param num1
     * @param num2
     * @return
     */
    public int countOperations(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }
        return countOperations(Math.min(num1, num2), Math.abs(num1 - num2)) + 1;
    }
}
