package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.util.Collections;

class RandomHelperTest {

    @Test
    void getRandom() {
        int[] random = RandomHelper.getRandom(10);
        System.out.println(Collections.singletonList(random));
    }
}
