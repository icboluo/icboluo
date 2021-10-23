package icboluo.util;

import java.util.Random;

/**
 * @author icboluo
 */
public class RandomHelper {

    private RandomHelper() {
    }

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
        Random random = new Random();
        return random.nextInt(b - a + 1) + a;
    }
}
