package com.icboluo.lambda;



import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author lp
 * java8引入了一个新的操作符"->"，该操作符称为箭头操作符或lambda操作符
 * 箭头操作符将Lambda表达式分成2部分
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式所需要执行的功能
 * Lambda表达式需要函数式接口的支持
 * <p>
 * java8 内置的4大核心函数式接口
 * consumer<T>：消费型接口    void accept(T t)
 * Supplier<T>：供给型接口    T get()
 * Function<T,R>：函数型接口  R apply(T t)
 * Predicate<T>：断言型接口   boolean test(T t)
 */
public class LambdaGrammarTest {
    /**
     * 无参无返回值
     */
    @Test
    public void test1() {
        //final 不用加，默认为final
        final int num = 0;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("true = " + num);
            }
        };
        r1.run();
        Runnable r2 = () -> System.out.println("false = " + false);
        r2.run();
    }

    /**
     * 有一个参数无返回值，如果只有一个参数，则参数的小括号可以不写
     * Lambda的参数列表数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型
     */
    @Test
    public void test2() {
        Consumer<String> c = x -> System.out.println("x = " + x);
        c.accept("cccc");

        //不能分开写，不能进行类型判断
        String[] strs = {"sss", "ddd"};
        List<String> list = new ArrayList<>();
    }

    /**
     * 2个参数有返回值
     * 如果有多条语句，lambda必须使用大括号
     * 若语句中只有一条语句，则大括号和return都可以省略
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("x = " + x);
            return Integer.compare(x, y);
        };
        System.out.println(com.compare(1, 2));

        Comparator<Integer> c = (x, y) -> Integer.compare(x, y);
        System.out.println(c.compare(3, 5));
    }
}