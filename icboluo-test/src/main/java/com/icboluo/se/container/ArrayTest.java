package com.icboluo.se.container;

import org.junit.Test;

import java.util.*;

/**
 * @author icboluo
 * @date 2020/6/5 15:10
 */
public class ArrayTest {

    @Test
    public void test2() {
        //of集合创建完毕后，后期不进行增删改操作
        List<Integer> list3 = List.of(10, 10, 10);
        //UnsupportedOperationException不支持的操作异常
        list3.add(10);

        //Set：无序，不可重复
        //IllegalArgumentException: duplicate element: 10 非法参数异常：重复的元素10
        Set.of(10, 10, 30);

        // Map:映射表集合，映射对象不能重复
        //IllegalArgumentException: duplicate key: jeck
        Map.of("jeck", 11, "faker", 13, "jeck", 20);
    }
}
