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

    /**
     * 竖着打印数组
     * <p>
     * arr 0  1  0  2  1  0  1  3  2  1  2  1
     * 3                       ||
     * 2           ||          || ||    ||
     * 1     ||    || ||    || || || || || ||
     * inx 0  1  2  3  4  5  6  7  8  9  10  11
     *
     * @param arr 数组存放每个柱子的高度
     */
    public static void endwaysPrint(int[] arr) {
        System.out.print("arr ");
        for (int j : arr) {
            System.out.print(j + "  ");
        }
        System.out.println();
        int maxVal = findMaxVal(arr);
        for (int r = maxVal; r > 0; r--) {
            for (int c = 0; c < arr.length; c++) {
                if (c == 0) {
                    System.out.print(" " + r + "  ");
                }
                if (arr[c] - r + 1 > 0) {
                    System.out.print("|| ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.print("inx ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

    public static int findMaxVal(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int v : arr) {
            max = Math.max(v, max);
        }
        return max;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
