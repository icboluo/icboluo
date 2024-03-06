package com.icboluo.leetcode.othersource;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2024-03-06 22:51
 */
class N_发电机墙 {
    public static void main(String[] args) {
        System.out.println(notNeighboring("MIMMI"));
        System.out.println(notNeighboring("MIIM"));
        System.out.println(notNeighboring("MIM"));
        System.out.println(notNeighboring("M"));
        System.out.println(notNeighboring("MMM"));
        System.out.println(notNeighboring("I"));
    }

    // 放发电机，发电机旁边必须有墙
    // 1.开头结尾补墙特殊处理
    public static int notNeighboring(String str) {
        // MMM 或者 开头是MM|结尾是MM 或者 仅M 的情况，均为不合法
        String mmm = "M" + str + "M";
        if (mmm.contains("MMM")) {
            return -1;
        }
        // 0表示I,1表示M，2表示已经放过
        char[] arr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'M') {
                arr[i] = 'M';
            } else {
                arr[i] = 'I';
            }
        }
        // 处理2个连续2的
        int mm = 0;
        while (true) {
            // 找到所有2个M的位置，给左右各一个电箱
            mm = str.indexOf("MM", mm + 2);
            if (mm == -1) {
                break;
            }
            arr[mm - 1] = 'Z';
            arr[mm + 2] = 'Z';
            arr[mm - 2] = 'I';
            arr[mm] = 'I';
            arr[mm + 1] = 'I';
            if (mm + 3 < arr.length) {
                arr[mm + 3] = 'I';
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'M') {
                if (i < arr.length - 1) {
                    arr[i + 1] = 'Z';
                    arr[i] = 'I';
                    if (i < arr.length - 2) {
                        arr[i + 2] = 'I';
                    }
                } else {
                    // 如果最后一个是M
                    arr[i - 1] = 'Z';
                    arr[i] = 'I';
                }
            }
        }
        return (int) IntStream.range(0, arr.length).map(i -> arr[i]).filter(e -> e == 'Z').count();
    }
}
