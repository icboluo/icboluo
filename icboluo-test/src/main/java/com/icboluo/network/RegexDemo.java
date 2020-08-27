package com.icboluo.network;

import java.util.Arrays;

/**
 * 与正则表达式相关的api
 * boolean matches(String regex) 告诉这个字符串是否匹配给定的 regular expression
 * String[] split(String regex) 将此字符串拆分为给定的 regular expression的匹配
 * Regex：正则表达式是用字符串写成的一种规则
 *
 * @author icboluo
 */
public class RegexDemo {
    public static void main(String[] args) {
        String ip = "192.168.0.1";
        String[] strings = ip.split("\\.");
        System.out.println(Arrays.toString(strings));

        matchesQQ("123456789");
        matchesTel("13709851737");
    }

    private static void matchesTel(String tel) {
        //必须1开头,第二位: 34578，全数字,总共11位
        boolean result = tel.matches("[1]{1}[34578]{1}[0-9]{9}");
        System.out.println(result);
    }

    private static void matchesQQ(String qq) {
        //必须全数字,不能0开头,最低5位,最高12位
        boolean result = qq.matches("[1-9]{1}[0-9]{4,11}");
        System.out.println(result);
    }

}
