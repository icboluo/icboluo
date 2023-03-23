package com.icboluo.util;

import org.junit.jupiter.api.Test;

class ArrayHelperTest {

    @Test
    void initArr() {
        int[][] ints = ArrayHelper.initArr(3, 4);
        ArrayHelper.print(ints);
    }
}
