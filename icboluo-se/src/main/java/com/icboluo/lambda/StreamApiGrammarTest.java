package com.icboluo.lambda;

import com.icboluo.object.Student;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * stream 不会储存元素，不会改变源对象
 * stream操作步骤：
 * 1.创建stream
 * 2.中间操作
 * 3.终止操作
 * <p>
 * 方法引用：若Lambda体中的内容已经有方法实现了，可以使用方法引用
 * 方法引用语法：
 * 对象::实例方法名,实例方法就是普通方法
 * 类::静态方法名
 * 类::实例方法名
 * 注意：Lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 * 构造器引用 className::new
 * 数组引用 Type::new
 *
 * @author lp
 */
public class StreamApiGrammarTest {
    /**
     * 对象：：实例方法名
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
        String s = sup.get();
        System.out.println("s = " + s);

        Supplier<Integer> sup2 = student::getAge;
        System.out.println(sup2.get());
    }

    /**
     * 类：：静态方法名
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;
        System.out.println("com1.compare(1, 2) = " + com1.compare(1, 2));
    }

    /**
     * 类：：实例方法名
     * 类名为什么能调实例方法呢？
     * 第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以用类名直接调用实例方法
     */
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
        boolean test = bp2.test("12", "12");
        System.out.println("test = " + test);
        boolean b = StringUtils.pathEquals("12", "12");
        System.out.println("b = " + b);
    }

    /**
     * 构造器引用：抽象方法的参数列表和构造方法参数列表一直就可以使用了
     */
    @Test
    public void test5() {
        Supplier<Student> sup = () -> new Student();

        Supplier<Student> sup2 = Student::new;
        Student student = sup2.get();
        System.out.println("student = " + student);
    }

    @Test
    public void test6() {
        Function<Integer, Student> fun = x -> new Student(x, "1");

        BiFunction<Integer, String, Student> bf = Student::new;
        System.out.println("bf.apply(1,\"zhu\") = " + bf.apply(1, "zhu"));
    }

    /**
     * 数组引用
     */
    @Test
    public void test7() {
        Function<Integer, String[]> fun = x -> new String[x];
        Function<Integer, String[]> fun2 = String[]::new;
        System.out.println(fun2.apply(20).length);
    }
}
