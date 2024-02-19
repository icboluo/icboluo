package com.icboluo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcelUtilTest {

    @Test
    void convertToTitle() {
        assertEquals("A", ExcelUtil.convertToTitle(1));
        assertEquals("AA", ExcelUtil.convertToTitle(27));
    }

    @Test
    void titleToNumber() {
        assertEquals(1, ExcelUtil.titleToNumber("A"));
        assertEquals(26, ExcelUtil.titleToNumber("Z"));
        assertEquals(1443, ExcelUtil.titleToNumber("BCM"));
    }
}
