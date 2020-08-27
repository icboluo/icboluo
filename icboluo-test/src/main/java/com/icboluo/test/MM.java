package com.icboluo.test;

import com.icboluo.ee.tomcat.Day01.optimize03.Demo01;
import lombok.Data;

import java.util.HashSet;

/**
 * integer i,j=100; i==j? 正确，虽然是引用数据类型，单在127以内，是去常量池中去找，不是去创建对象
 * 什么时候使用匿名内部类？？
 * 当我们需要一个父类的对象，而这个对象只被使用一次，这个对象的类没必须特意创建.java源文件，此时就直接使用匿名内部类即可
 *  JDK 和 JRE 有什么区别？
 * JDK：Java Development Kit 的简称，java 开发工具包，提供了 java 的开发环境和运行环境。
 * JRE：Java Runtime Environment 的简称，java 运行环境，为 java 的运行提供了所需环境。
 * 具体来说 JDK 其实包含了 JRE，同时还包含了编译 java 源码的编译器 javac，还包含了很多 java 程序调试和分析的工具。简单来说：如果你需要运行 java 程序，只需安装 JRE 就可以了，如果你需要编写 java 程序，需要安装 JDK。
 *
 * @author icboluo
 * @date 2020-07-31 15:23
 */
@Data
public class MM {
    public static void main(String[] args) {
    }
}
