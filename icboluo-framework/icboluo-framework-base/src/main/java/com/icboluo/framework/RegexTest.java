package com.icboluo.framework;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /*
        匹配单个字符： the 直接写即可
        [a-c] a-c区间值
        [^a-c]非a-c 在中括号外面代表匹配开始
        . 除了换行符（\n,\r）以外的所有字符[^\n\r]
        [\s]所有空白符，包括换行，[\S]非空白符，不包括换行
        \b 匹配单词边界，即字与空格间的位置，\B非单词边界匹配
        [\w]字母、数字、下划线；[a-zA-Z0-9_] [\d] 数字 [0-9]
        \n 换行符 \r回车符 \t制表符
        次数：? {0,1} +{1,} *{0,}
        ^$ 开始结束
    */
    @Test
    public void test1() {
    }

    @Test
    public void test2() {
        String 标签 = "<h1>h标签</h1>";
        printMatch(标签, "<.+>");
        printMatch(标签, "<.+?>");// ?会将默认贪婪匹配切换为懒惰匹配

        String b = "The color in include learning";
        printMatch(b, "\\bin\\B"); // in开头的单词
    }

    @Test
    public void test3() {
        String abc = "abacab";
        printMatch(abc, "a(b|c)"); // 分支
        printMatch(abc, "(ab)(?:ac)\\1"); // 分组,?:表示非捕获元，就是不计入分组
        printMatch(abc, "a(?=c)");// 断言?=,?!,?<=,?<!
        System.out.println("----------------------------");
        String abcd = "abbaab";
        printMatch(abcd, "(a(b|c))|(b(a|c))\\1");
    }

    @Test
    public void test4() {
        // 取出字符串中的年月日
        String str = "Today is 2024-12-12 or 2024-13-13 or 0000-00-00";
        printMatch(str, """
                \\d{4}-\\d{2}-\\d{2}""");
        System.out.println("-----------------------------------------------------------");
        printMatch(str, """
                \\d{4}-((1[012])|(0[1-9]))-((3[01])|([12]\\d)|(0[1-9]))""");
        // ip 地址
        String ip = "ip have 192.168.2.1, 0.0.0.0, 001.001.001.001, 9.99.99.9999";
        printMatch(ip, """
                \\d+\\.\\d+\\.\\d+\\.\\d+""");
        System.out.println("------------------------------------------------------------");
        // 一位数字，合法范围是0-9，二位数字，合法范围是10-99，1** ->100-199,2** 200-249 250-255
        printMatch(ip, """
                \\b(((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\b""");

        printMatch(ip, """
                \\b(((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}\\2\\b""");
    }

    public static void printMatch(String str, String regex) {
        // 创建Pattern对象
        Pattern pattern = Pattern.compile(regex);

        // 创建Matcher对象
        Matcher matcher = pattern.matcher(str);

        // 查找匹配的子串
        while (matcher.find()) {
            System.out.println("Found: " + matcher.group());
        }
    }
}
