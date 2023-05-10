package com.icboluo.leetcode.math.最大公约数;

/**
 * @author icboluo
 * @since 2022-12-08 22:27
 */
class N1071_字符串的最大公约数 {
    public String gcdOfStrings(String str1, String str2) {
// 字符串a由（组件 组件 组件）构成，b由（组件 组件）构成；所以 a+b=b+a，否则说明他们的组件不同，不能拥有最大公约数
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        int gcdVal = gcd(str1.length(), str2.length());
        return str2.substring(0, gcdVal);
    }

    private int gcd(int a ,int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
