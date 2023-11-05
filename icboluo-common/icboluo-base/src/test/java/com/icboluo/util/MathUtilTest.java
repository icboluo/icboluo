package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathUtilTest {

    @Test
    void divide() {
        assertEquals(0, MathUtil.divide(10, 3, 2, RoundingMode.CEILING).compareTo(BigDecimal.valueOf(3.34)));
    }

    @Test
    void dividePercentage() {
        assertEquals(0, MathUtil.dividePercentage(10, 100).compareTo(BigDecimal.valueOf(10)));
        System.out.println(MathUtil.dividePercentage(0.02, 100000, 5));
        System.out.println(MathUtil.dividePercentage(9999998, 9999999, 4));
    }
}
