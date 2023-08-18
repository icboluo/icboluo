package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.util.Collections;

class RandomUtilTest {

    @Test
    void getRandom() {
        int[] random = RandomUtil.getRandom(10);
        System.out.println(Collections.singletonList(random));
    }
}
