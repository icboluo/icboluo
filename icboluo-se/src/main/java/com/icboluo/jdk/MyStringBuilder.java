package com.icboluo.jdk;

/**
 * @author icboluo
 * @since 2022-01-10 15:52
 */
public class MyStringBuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
//        append 会调用AbstractStringBuilder类的方法，先进行扩容，再copy字节数组，再count+
        // count+会影响扩容大小，如果获得的count是旧值，就会索引越界，length变小
        // StringBuffer有toString缓存（也并没有多大实际作用，builder没有（线程不安全加了弊端太大）
        sb.append("7");
    }
}
