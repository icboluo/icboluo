package com.icboluo.util;

/**
 * 操作数组的工具类
 * row代表行，column代表列
 *
 * @author icboluo
 */
public class ArrayHelper {
    /**
     * 打印二维数组
     *
     * @param arr 要打印的数组
     */
    public static void print(int[][] arr) {
        for (int[] row : arr) {
            for (int c = 0; c < arr[0].length; c++) {
                //\t为制表符，\n为换行符，%d代表元素
                System.out.printf("%d\t", row[c]);
            }
            System.out.println();
        }
    }

    /**
     * 反转二维数组
     *
     * @param arr 要反转的数组
     */
    public static int[][] reverse(int[][] arr) {
        int[][] newArr = new int[arr[0].length][arr.length];
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                int temp = arr[r][c];
                newArr[c][r] = temp;
            }
        }
        return newArr;
    }
}
