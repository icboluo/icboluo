package com.icboluo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcelHelpTest {

    @Test
    void titleToNumber() {
        assertEquals(1, ExcelHelp.titleToNumber("A"));
        assertEquals(26, ExcelHelp.titleToNumber("Z"));
        assertEquals(1443, ExcelHelp.titleToNumber("BCM"));
    }
}
