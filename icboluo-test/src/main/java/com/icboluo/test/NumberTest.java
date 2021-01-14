package com.icboluo.test;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author icboluo
 */
public class NumberTest {

    @Test
    public void test1() {
        // int : -2147483648 ~ 2147483647
        int minValue = Integer.MIN_VALUE;
        System.out.println("minValue = " + minValue);
        int maxValue = Integer.MAX_VALUE;
        System.out.println("maxValue = " + maxValue);

        // 1byte = 8bit
        int size = Integer.SIZE;
        System.out.println("size = " + size);

        // cls -> int
        Class<Integer> cls = Integer.TYPE;
        System.out.println("cls = " + cls);
    }

    @Test
    public void test4() {
        BigDecimal bd = new BigDecimal(999.000000).setScale(6);
        BigDecimal bd1 = bd.stripTrailingZeros();
        String s = bd1.toString();
        System.out.println("s = " + s);
    }

    @Test
    public void test5() {
        String s = "495.0000000";
        double i2 = Double.parseDouble(s);
        BigDecimal bn = new BigDecimal(495.0000000);
        BigDecimal bigDecimal = new BigDecimal(s);
        int i1 = bn.intValue();
        double v = Double.parseDouble(s);
        int i = Integer.parseInt(s, 0, s.indexOf("."), 10);
        System.out.println("i = " + i);
    }
}
