package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class NumberHelperTest {

    @Test
    void divice() {
        BigDecimal divide = NumberHelper.divide(10, 3, 2,RoundingMode.CEILING);
        System.out.println("divide = " + divide);
    }
}