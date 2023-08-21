package com.icboluo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcelHelpTest {

    @Test
    void convertToTitle() {
        assertEquals("A", ExcelHelp.convertToTitle(1));
        assertEquals("AA", ExcelHelp.convertToTitle(27));
    }

    @Test
    void titleToNumber() {
        assertEquals(1, ExcelHelp.titleToNumber("A"));
        assertEquals(26, ExcelHelp.titleToNumber("Z"));
        assertEquals(1443, ExcelHelp.titleToNumber("BCM"));
    }
}
