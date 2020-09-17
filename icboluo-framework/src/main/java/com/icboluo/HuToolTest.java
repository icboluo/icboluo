package com.icboluo;

import cn.hutool.core.lang.UUID;
import org.junit.jupiter.api.Test;

/**
 * @author icboluo
 * @date 2020-09-18 00:32
 */
public class HuToolTest {
    @Test
    public void test1() {
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid = " + uuid);
    }
}
