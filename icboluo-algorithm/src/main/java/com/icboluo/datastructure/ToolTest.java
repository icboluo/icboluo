package com.icboluo.datastructure;

import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

/**
 * @author icboluo
 * @since 2025-03-10 8:37
 */
class ToolTest {

    @Test
    public void test1() {
        double gongXian = 8;
        double cur = 8;
        int totalDay = MathUtil.divide(gongXian, cur - 8, 0).intValue();
        double[] arr = {};

        System.out.println(STR."total day = \{totalDay}");
    }

    @Test
    public void test2() {
        double a = 3115.4;
        double b = 2690.59;
        double sum = a + b;
        System.out.println(STR."sum: \{sum}");
        System.out.println(a * 360 - 710000);
        System.out.println(b * 360 - 650000);
        System.out.println(a - a * 61 / 71);
        System.out.println(a * 61 / 71);
    }
}
