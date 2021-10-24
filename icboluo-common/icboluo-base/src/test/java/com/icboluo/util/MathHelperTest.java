package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

class MathHelperTest {

    @Test
    void divice() {
        BigDecimal divide = MathHelper.divide(10, 3, 2,RoundingMode.CEILING);
        System.out.println("divide = " + divide);
    }
}
