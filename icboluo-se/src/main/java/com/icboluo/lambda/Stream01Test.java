package com.icboluo.lambda;

import com.icboluo.object.Student;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用：若Lambda体中的内容已经有方法实现了，可以使用方法引用;有现成的就不用自己写了
 * 方法引用语法：如下
 *
 * @author icboluo
 */
public class Stream01Test {
    /**
     * 对象::实例方法名
     */
    @Test
    public void test1() {
        PrintStream ps1 = System.out;
        Consumer<String> con = x -> ps1.println("x = " + x);
        con.accept("ddd");

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;
        con1.accept("fff");

        Consumer<String> con2 = System.out::println;
        con2.accept("ttt");
    }

    @Test
    public void test2() {
        Student student = new Student();
        Supplier<String> sup = () -> student.getName();
        String name = sup.get();
        System.out.println("name = " + name);

        Supplier<Integer> age = student::getAge;
        System.out.println(age.get());
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test3() {
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com2 = Integer::compare;
        System.out.println("com2.compare(1, 2) = " + com2.compare(1, 2));
    }

    /**
     * 类::实例方法名
     * 类名为什么能调实例方法呢？
     * 场景：第一个参数是实例方法的调用者，第二个（其余）参数是实例方法的参数时
     * 第一个参数获取参数类型；调用成员方法，把剩下的参数传递进去
     */
    @Test
    public void test4() {
        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
        boolean equals = bp2.test("12", "12");
        System.out.println("equals = " + equals);
    }

    /**
     * 构造器引用：抽象方法的参数列表和构造方法参数列表一直就可以使用了
     * 和调用实例方法原理类似
     */
    @Test
    public void test5() {
        Supplier<Student> sup = () -> new Student();

        Supplier<Student> sup2 = Student::new;
        Student student = sup2.get();
        System.out.println("student = " + student);

        Function<Integer, Student> fun = x -> new Student(x, "1");
        BiFunction<Integer, String, Student> bf = Student::new;
        System.out.println("bf.apply(1,zhu) = " + bf.apply(1, "zhu"));
    }


    /**
     * 数组引用
     * 和构造器引用类似，用数组的构造器即可，参数是数组的长度
     */
    @Test
    public void test7() {
        Function<Integer, String[]> fun1 = x -> new String[x];
        Function<Integer, String[]> fun2 = String[]::new;
        System.out.println(fun2.apply(20).length);
    }
}
