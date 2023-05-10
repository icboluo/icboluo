package com.icboluo.leetcode.after_0000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-20 14:55
 */
class N0006_蛇形矩阵 {
    public static void main(String[] args) {
        var cla = new N0006_蛇形矩阵();
        String str = "PAYPALISHIRING";
        int row = 3;
        String res = cla.convert(str, row);
        System.out.println("res = " + res);
    }

    // 建立空数组，分为多行，将每行的数组拼接就可以了
    public String convert(String str, int row) {
        String[] arr = new String[row];
        Arrays.fill(arr, "");
        char[] chars = str.toCharArray();
        int idx = 0;
        while (true) {
            // 一次完整的循环就是先竖再斜上
            for (int i = 0; i < row; i++) {
                if (idx > str.length() - 1) {
                    return Arrays.stream(arr).reduce(String::concat).orElse("");
                }
                // 因为这里是后加，所以前面不需要等号
                arr[i] += chars[idx++];
            }
            for (int i = row - 2; i > 0; i--) {
                if (idx > str.length() - 1) {
                    return Arrays.stream(arr).reduce(String::concat).orElse("");
                }
                arr[i] += chars[idx++];
            }
        }
    }
}
