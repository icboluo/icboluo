package com.icboluo.util;

/**
 * @author icboluo
 */
public class RandomHelper {
    /**
     * 获得随机数数组
     *
     * @param count 随机数的个数
     * @return 随机数数组
     */
    public static int[] getRandom(int count) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = (int) (Math.random() * count * 10);
        }
        return arr;
    }
}
