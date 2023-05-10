package com.icboluo.leetcode.math.最大公约数;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-12-05 23:25
 */
class N0914_最大公约数 {
    public static void main(String[] args) {
        N0914_最大公约数 cla = new N0914_最大公约数();
        int[] arr = new int[]{1, 2, 3, 4, 4, 3, 2, 1};
        boolean b = cla.hasGroupsSizeX(arr);
        System.out.println("b = " + b);
    }

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deck) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int gcd = map.values().iterator().next();
        for (Integer value : map.values()) {
            gcd = gcd(value, gcd);
        }
        return gcd > 1;
    }

    private int gcd(Integer a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
