package com.icboluo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanUtilTest {

    @Test
    void allIsNull() {
        int[] arr1 = new int[10];
        boolean allIsNullInt = BeanUtil.allIsNull(arr1);
        assertFalse(allIsNullInt);

        String[] arr2 = new String[10];
        boolean allIsNullStr = BeanUtil.allIsNull(arr2);
        assertTrue(allIsNullStr);
    }
}
