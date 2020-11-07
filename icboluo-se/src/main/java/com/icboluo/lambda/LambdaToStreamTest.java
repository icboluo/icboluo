package com.icboluo.lambda;

import com.icboluo.dataobject.Student;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;

public class LambdaToStreamTest {

    List<Student> stus = Arrays.asList(
            //new Student(22, "one"),
            new Student(1, "two"),
            new Student(2, "three"),
            new Student(3, "three")
    );

    /**
     * 使用
     */
    @Test
    public void test1() {
        Integer intNum = operation(100, x -> x * x);
        System.out.println("intNum = " + intNum);
        Integer operation = operation(200, x -> x + 999);
        System.out.println("operation = " + operation);
    }

    @Test
    public void test2() {
        Integer integer = intHander(12345, i -> ++i);
        System.out.println("integer = " + integer);
    }

    @Test
    public void test3() {
        longHander(100L, 200L, (x, y) -> x + y);
    }


    @Test
    public void test4() {
        happy(1000, m -> System.out.println("消费了好多钱" + m));
    }

    @Test
    public void test5() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        System.out.println("numList = " + numList);
    }

    @Test
    public void test6() {
        String s = strHander("        /d/d/d/d sssssssss", str -> str.substring(7, 11));
        System.out.println("s = " + s);
    }

    @Test
    public void test7() {
        List<String> list = Arrays.asList("dd", "dds", "ddsd");
        List<String> list1 = filterStr(list, s -> s.length() > 2);
        System.out.println("list1 = " + list1);
    }

    private List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                strList.add(s);
            }
        }
        return strList;

    }

    private String strHander(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    private List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    private void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    private void longHander(Long l1, Long l2, BiFunction<Long, Long, Long> bf) {
        System.out.println(bf.apply(l1, l2));
    }

    private Integer intHander(int i, Function<Integer, Integer> function) {
        return function.apply(i);
    }

    private Integer operation(Integer num, Function<Integer, Integer> function) {
        return function.apply(num);
    }

}
