package com.icboluo.framework;

import java.util.Base64;

/**
 * @author icboluo
 * @since 2024-06-28 上午12:31
 */
class Base64Demo {
    public static void main(String[] args) {
        String str = "hello world";
        // 将字符串str进行Base64编码, Base64编码之后的结果是一致的
        String encode = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println("Base64编码后的字符串: " + encode);

        // 将Base64编码后的字符串进行解码
        String decode = new String(Base64.getDecoder().decode(encode));
        System.out.println("Base64解码后的字符串: " + decode);
    }
}
