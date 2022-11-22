package com.icboluo.leetcode.after_0400;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-22 21:28
 */
class N0415_0989_字符串相加数组相加 {
    // 字符串相加
    public String addStrings(String num1, String num2) {
        int weiShu = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < num1.length() || i < num2.length() || weiShu != 0) {
            i++;
            int a = num1.length() - i >= 0 ? num1.charAt(num1.length() - i) - '0' : 0;
            int b = num2.length() - i >= 0 ? num2.charAt(num2.length() - i) - '0' : 0;
            int temp = a + b + weiShu;
            sb.append(temp % 10);
            weiShu = temp / 10;
        }
        return sb.reverse().toString();
    }

    // 0989 数组相加
    public List<Integer> addToArrayForm(int[] num, int k) {
        char[] chars = Integer.toString(k).toCharArray();
        int i = 1;
        int weiShu = 0;
        List<Integer> list = new ArrayList<>();
        while (i <= chars.length && i <= num.length) {
            int temp = num[num.length - i] + Character.digit(chars[chars.length - i], 10) + weiShu;
            if (temp >= 10) {
                weiShu = 1;
                temp -= 10;
            } else {
                weiShu = 0;
            }
            i++;
            list.add(0, temp);
        }
        while (i <= chars.length) {
            int temp = Character.digit(chars[chars.length - i], 10) + weiShu;
            if (temp >= 10) {
                weiShu = 1;
                temp -= 10;
            } else {
                weiShu = 0;
            }
            i++;
            list.add(0, temp);
        }
        while (i <= num.length) {
            int temp = num[num.length - i] + weiShu;
            if (temp >= 10) {
                weiShu = 1;
                temp -= 10;
            } else {
                weiShu = 0;
            }
            i++;
            list.add(0, temp);
        }
        if (weiShu != 0) {
            list.add(0, weiShu);
        }
        return list;
    }

    public List<Integer> addToArrayForm2(int[] num, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = num.length - 1; i >= 0; i--) {
            int sum = num[i] + k;
            int mo = sum % 10;
            k = sum / 10;
            list.add(0, mo);
        }
        while (k > 0) {
            list.add(0, k % 10);
            k = k / 10;
        }
        return list;
    }
}
