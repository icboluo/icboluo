package com.icboluo.jdk;

/**
 * @author icboluo
 * @date 2022-01-10 15:52
 */
public class MyStringBuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
//        append 会调用AbstractStringBuilder类的方法，先进行扩容，再copy字节数组，再count+
        sb.append("7");
    }
}
