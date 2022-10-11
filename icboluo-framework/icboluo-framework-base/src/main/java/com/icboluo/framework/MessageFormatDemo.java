package com.icboluo.framework;


import com.icboluo.function.CiConsumer;

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
        Message format 和 String.format是一样的。可以替换
         */
        String format = MessageFormat.format("{0} is zero,{0} is zero, {2} is two", 0, 1, 2);
        System.out.println("format = " + format);
    }
}
