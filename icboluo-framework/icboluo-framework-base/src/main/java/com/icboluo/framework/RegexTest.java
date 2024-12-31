package com.icboluo.framework;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 正则 Regex：正则表达式是用字符串写成的一种规则
 *
 * @author icboluo
 * @since 2024-12-31 18:19
 */
class RegexTest {

    /**
     * 与正则表达式相关的api
     *
     * @see String#matches(String) 告诉这个字符串是否匹配给定的 regular expression
     * @see String#split(String)  将此字符串拆分为给定的 regular expression的匹配
     */
    @Test
    public void matchApi() {
        String ip = "192.168.0.1";
        String[] strings = ip.split("\\.");
        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void matchTelephone() {
        // 必须1为开头,第二位: 34578，全数字,总共11位
        String tel = "13709851737";
        boolean result = tel.matches("[1]{1}[34578]{1}[0-9]{9}");
        System.out.println(result);
    }

    @Test
    public void matchQQ() {
        String qq = "123456789";
        // 必须全数字,不能0为开头,最低5位,最高12位
        boolean result = qq.matches("[1-9]{1}[0-9]{4,11}");
        System.out.println(result);
    }
}
