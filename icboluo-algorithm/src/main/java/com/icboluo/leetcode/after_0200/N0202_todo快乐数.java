package com.icboluo.leetcode.after_0200;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2020-09-29 19:47
 */
public class N0202_todo快乐数 {
    public static void main(String[] args) {
        N0202_todo快乐数 cla = new N0202_todo快乐数();
        boolean r = cla.isHappy(19);
        System.out.println("r = " + r);
    }

    /**
     * 每一位上平方求和，最终结果是否为1
     * 双层循环外层循环每一次的结果值，内层循环计算结果
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int next = 0;
            // 每个位数的平方和
            while (n != 0) {
                next += (n % 10) * (n % 10);
                n = n / 10;
            }
            n = next;
            // 一直算，总会为1的，只有死循环的时候不会为1
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
        return true;
    }
}
