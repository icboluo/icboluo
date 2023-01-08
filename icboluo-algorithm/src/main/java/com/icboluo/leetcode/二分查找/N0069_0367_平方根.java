package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2023-01-07 23:25
 */
class N0069_0367_平方根 {
    /**
     * 取最近的
     * 对x取平方根，并向下取整 FIXME 超时
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1;
        int right = x;
        while (true) {
            int mid = left + (right - left) >> 1;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            }
        }
    }

    /**
     * 0367 有效完美平方，判断一个数是否能开根号 FIXME 超时
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while (left <= num) {
            int mid = left + (right - left) >> 1;
            if (mid * mid > num) {
                right = mid - 1;
            } else if (mid * mid < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 0633 平方数之和；是否存在2个数a，b 使a*a+b*b=c FIXME 越界
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            int temp = left * left + right * right;
            if (temp > c) {
                right--;
            } else if (temp < c) {
                left++;
            } else {
                return true;
            }
        }
        return false;
    }
}
