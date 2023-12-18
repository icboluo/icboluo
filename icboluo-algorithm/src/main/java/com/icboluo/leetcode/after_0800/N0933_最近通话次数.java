package com.icboluo.leetcode.after_0800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-12-18 22:41
 */
class N0933_最近通话次数 {
    List<Integer> list;

    // 最近通话次数 FIXME
    public N0933_最近通话次数() {
        list = new ArrayList<>();
    }

    // 在时间t添加请求，返回过去发生的请求数
    public int ping(int t) {
        list.add(t);
        return (int) list.stream().takeWhile(e -> e > t).count();
    }

    public static void main(String[] args) {
        var cla = new N0933_最近通话次数();
        System.out.println(cla.ping(1));
        System.out.println(cla.ping(100));
        System.out.println(cla.ping(3001));
        System.out.println(cla.ping(3002));
    }
}
