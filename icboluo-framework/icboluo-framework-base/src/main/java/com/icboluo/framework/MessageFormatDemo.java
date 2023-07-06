package com.icboluo.framework;


import com.icboluo.function.CiConsumer;

import java.text.DecimalFormat;
import java.text.MessageFormat;

/**
 * @author icboluo
 * @since 2021-06-07 21:06
 */
public class MessageFormatDemo {
    /**
     * 部分情况可以抽出来做成员变量方便使用
     */
    CiConsumer<Integer, Integer, Integer> ci = (a, b, c) -> MessageFormat.format("{0} is zero,{0} is zero, {2} is two", 0, 1, 2);

    public static void main(String[] args) {
        /*
        Message format 和 String.format 在功能上是一样的，可以替换，在写法上是不一样的
         */
        System.out.println("MessageFormat = " + MessageFormat.format("{0} is zero,{0} is zero, {2} is two", 0, 1, 2));
        System.out.println(String.format("{0} is zero,{0} is zero, {2} is two", 0, 1, 2));
        System.out.println(String.format("%d is zero,%d is zero, %d is two", 0, 0, 2));

        // 数字格式化 1.DecimalFormat
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        decimalFormat.format(100);// 0100
    }
}
