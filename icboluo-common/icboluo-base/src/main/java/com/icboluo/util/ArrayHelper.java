package com.icboluo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;

/**
 * 操作数组的工具类
 * row代表行，column代表列
 *
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        reverse(arr, left, right);
    }

    /**
     * 反转数组
     *
     * @param arr   要反转的数组
     * @param left  数组开始反转索引
     * @param right 数组结束反转索引
     */
    public static void reverse(int[] arr, int left, int right) {
        int temp;
        while (left < right) {
            temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
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

    /**
     * 查询数组中最大值
     *
     * @param arr 数组
     * @return 数组中的最大值
     */
    public static int findMaxVal(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int v : arr) {
            max = Math.max(v, max);
        }
        return max;
    }

    /**
     * 交换数组中元素的位置
     *
     * @param arr 数组
     * @param i   要交换的第一个索引
     * @param j   要交换的第二个索引
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 将数组存储到map中，如果map中已经出现，就+1
     *
     * @param arr 数组
     * @return k 数组元素，v 出现次数
     */
    public static Map<Integer, Integer> toMap(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 1));
        }
        return map;
    }

    /**
     * 初始化一个为default的二维数组
     *
     * @param n 行数
     * @param m 列数
     * @return 初始化后的二维数组
     */
    public static int[][] initArr(int n, int m) {
        int[][] v = new int[n][m];
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 1;
        }
        Arrays.fill(v[0], 0);
        return v;
    }

    /**
     * 生成递增的数组，第一个值从1开始
     *
     * @param max 数组的最大值
     * @return 生成的递增数组
     */
    public static int[] generateIncrementArr(int max) {
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    /**
     * 最长递增子序列（Longest Increasing Subsequence，简写 LIS）
     *
     * @param arr 待查找数组
     * @return 最长递增子序列的长度
     */
    public static int lis(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
//                只有后面比前面大的时候需要更新
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else {
//                    当然也可以给数组初始化1的值
                    dp[i] = Math.max(dp[i], 1);
                }
            }
        }
        return findMaxVal(dp);
    }

    public static <T, V> List<T> sort(List<T> list, Function<T, V> function, V[] arr) {
        return list.stream()
                .sorted((a, b) -> {
//                    这里设置的值是-1，其他的元素会在前面显示
                    int aInx = -1;
                    int bInx = -1;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].equals(function.apply(a))) {
                            aInx = i;
                        }
                        if (arr[i].equals(function.apply(b))) {
                            bInx = i;
                        }
                    }
                    return aInx - bInx;
                }).toList();
    }

    public static <T> boolean allEleIsEmpty(T[] row) {
        for (T t : row) {
            if (!ObjectUtils.isEmpty(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印一个表格，类似于打印一个Excel单元格
     *
     * @param str 字符串
     */
    public static void printTable(String str) {
        System.out.print(" ");
        for (int i = 0; i < str.toCharArray().length; i++) {
            System.out.println(" " + str.charAt(i));
        }
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }
    }

    public static <T> Set<T> toSet(T[] arr) {
        if (arr == null) {
            return null;
        }
        Set<T> ans = new HashSet<>();
        Collections.addAll(ans, arr);
        return ans;
    }

    public static <T> List<T> toList(T[] arr) {
        if (arr == null) {
            return null;
        }
        List<T> ans = new ArrayList<>();
        Collections.addAll(ans, arr);
        return ans;
    }

    public static <T> Collection<T> toColl(T[] arr) {
        if (arr == null) {
            return null;
        }
        List<T> ans = new ArrayList<>();
        Collections.addAll(ans, arr);
        return ans;
    }
}
