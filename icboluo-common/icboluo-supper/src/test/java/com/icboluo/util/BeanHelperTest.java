package com.icboluo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanHelperTest {

    @Test
    void allIsNull() {
        int[] arr1 = new int[10];
        boolean allIsNullInt = BeanHelper.allIsNull(arr1);
        assertFalse(allIsNullInt);

        String[] arr2 = new String[10];
        boolean allIsNullStr = BeanHelper.allIsNull(arr2);
        assertTrue(allIsNullStr);
    }
}
