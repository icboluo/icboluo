package com.icboluo.leetcode.after_0800;

import java.util.TreeSet;

/**
 * @author icboluo
 * @since 2023-12-18 22:41
 */
class N0933_最近通话次数 {
    TreeSet<Integer> set;

    // 最近通话次数
    public N0933_最近通话次数() {
        set = new TreeSet<>();
    }

    // 在时间t添加请求，返回过去发生的请求数
    public int ping(int t) {
        set.add(t);
        // optimize 找出大于等于这部分的
        return set.tailSet(t - 3000).size();
    }

    public static void main(String[] args) {
        var cla = new N0933_最近通话次数();
        System.out.println(cla.ping(1));
        System.out.println(cla.ping(100));
        System.out.println(cla.ping(3001));
        System.out.println(cla.ping(3002));
    }
}
