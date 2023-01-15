package com.icboluo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomHelper {

    private static final Random RANDOM = new Random();

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

    /**
     * 闭区间上的随机数
     *
     * @param a 左区间
     * @param b 右区间
     * @return 随机数
     */
    public static int interval(int a, int b) {
        return RANDOM.nextInt(b - a + 1) + a;
    }

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }
}
