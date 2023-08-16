package com.icboluo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayHelperTest {

    @Test
    void initArr() {
        int[][] ints = ArrayHelper.initArr(3, 4);
        ArrayHelper.print(ints);
        assertEquals(ints.length, 3);
    }
}
