package com.icboluo.lambda;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Lambda表达式需要函数式接口的支持
 * <p>
 * Java 内置的4大核心函数式接口（有好多其他的接口，可以自行查找
 *
 * @author icboluo
 * @see java.util.function.Consumer：消费型接口    void accept(T t)
 * @see java.util.function.Supplier：供给型接口    T get()
 * @see java.util.function.Function：函数型接口  R apply(T t)
 * @see java.util.function.Predicate：断言型接口   boolean test(T t)
 */
public class Lambda01Test {
    /**
     * 标准lambda表达式
     * "->"，该符号称为箭头操作符或lambda操作符
     * 箭头操作符将Lambda表达式分成2部分
     * 左侧：Lambda表达式的参数列表
     * 右侧：Lambda表达式所需要执行的功能
     */
    @Test
    public void test1() {
        BiFunction<Integer, Short, Integer> absBiFun = (Integer a, Short b) -> {
            if (a >= 0 && b >= 0) {
                return a + b;
            }
            return Math.abs(a) + Math.abs(b);
        };
        Integer ans = absBiFun.apply(2, (short) -1);
        System.out.println("ans = " + ans);
    }

    /**
     * 2个参数有返回值
     * 如果有多条语句，lambda必须使用大括号
     * 若语句中只有一条语句，则大括号和return和;号都可以省略
     * Lambda的参数列表数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("x = " + x);
            return Integer.compare(x, y);
        };
        System.out.println(com.compare(1, 2));

        Comparator<Integer> c = (x, y) -> Integer.compare(x, y);
        System.out.println(c.compare(3, 5));
    }


    /**
     * 有一个参数无返回值
     * 有一个参数：则参数的小括号可以不写
     */
    @Test
    public void test3() {
        Consumer<String> c = x -> System.out.println("x = " + x);
        c.accept("cccc");

        //不能分开写，不能进行类型判断
        String[] strs = {"sss", "ddd"};
        List<String> list = new ArrayList<>();
    }


    /**
     * 无参无返回值
     */
    @Test
    public void test4() {
        //final 不用加，默认为final
        final int num = 0;
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("true = " + num);
            }
        };
        run.run();
        Runnable runSimple = () -> System.out.println("false = " + false);
        runSimple.run();
    }
}
