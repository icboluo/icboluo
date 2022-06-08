package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

class MathUtilTest {

    @Test
    void divice() {
        BigDecimal divide = MathUtil.divide(10, 3, 2,RoundingMode.CEILING);
        System.out.println("divide = " + divide);
    }
}
