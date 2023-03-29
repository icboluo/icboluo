package com.icboluo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KkvMapTest {

    @Test
    void put() {
        KkvMap<String, String, Integer> kkvMap = new KkvMap<>();
        kkvMap.put("张三", "年龄", 18);
        kkvMap.put("李四", "年龄", 19);
        kkvMap.put("王五", "年龄", 20);
        kkvMap.put("张三", "学号", 101);

        Integer age = kkvMap.get("张三", "年龄");
        assertEquals(18, age);
        Integer id = kkvMap.get("张三", "学号");
        assertEquals(101, id);
    }
}
