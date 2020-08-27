package com.icboluo.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

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


    /**
     * BigDecimal除法如果除不尽，需要第3个参数
     */
    @Test
    public void test2() {
        String s = "11";
        BigDecimal bd = new BigDecimal("50");
        //BigDecimal divide = bd.divide(new BigDecimal(s), 2, BigDecimal.ROUND_HALF_UP);
    }

    @Test
    public void test3() {
        String s = new Date().toString();
        System.out.println("s = " + s);
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
