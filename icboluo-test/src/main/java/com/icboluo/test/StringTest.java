package com.icboluo.test;

import com.icboluo.dataobject.Student;
import lombok.NonNull;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StringTest {
    List<Student> stus = Arrays.asList(
            new Student(1, "one", Student.Status.BUSY),
            new Student(2, "two", Student.Status.FREE),
            new Student(3, "three", Student.Status.VOCATION),
            new Student(4, "four", Student.Status.BUSY),
            new Student(5, "five", Student.Status.FREE),
            new Student(5, "five", Student.Status.FREE),
            new Student(5, "five", Student.Status.FREE)
    );

    @Test
    public void test1() {
        //@811
        String s1 = "a" + "b" + "c";
        //@811
        String s2 = "abc";
        String s3 = "ab";
        //@812
        String s4 = s3 + "c";
    }

    /**
     * 求数组中每个元素是否对称
     */
    @Test
    public void test2() {
        String[] arr = {"010", "3223", "666", "7890987", "123123"};
        for (String str : arr) {
            StringBuilder sb = new StringBuilder(str);
            if (sb.reverse().toString().equals(str)) {
                System.out.println("sb = " + sb);
            }
        }
    }

    @Test
    public void test() {
        long l = System.currentTimeMillis();
        String substring = String.valueOf(l).substring(0, 10);
        System.out.println("l = " + substring);
        Date date = new Date(l);
        System.out.println("date = " + date);
    }

    @Test
    public void test6() {
        AtomicInteger i = new AtomicInteger();
        stus.forEach(s -> {
            i.getAndIncrement();
            System.out.println("andIncrement = " + i);
        });
    }



    @Test
    public void test15() {
        String s = "sdata=%7B%22waveoryid%22%3";
        String substring = s.substring(6);
        System.out.println("substring = " + substring);
    }

    @Test
    public void test16() {
        BigDecimal bd = new BigDecimal("52.79999923706055");
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(bd);
        System.out.println("format = " + format);
    }

    @Test
    public void test17() {
//将数组长度为偶数的数组进行倒序排序，并打印(不准新建数组，不准用工具类打印)
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


    }

    @Test
    public void test18() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (j <= i) {
                    System.out.print(i + "*" + j + "=" + i * j + " ");
                    if (i == j) {
                        System.out.println();
                    }
                }
            }
        }
    }

    @Test
    public void test19() {
        Integer i = null;
        aaa(i);
    }

    @Test
    public void test20() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(null);
        list.add("11");
        list.add("22");
        list.stream()
                .sorted((s1, s2) -> {
                    if (s1 == null) {
                        s1 = "0";
                    }
                    if (s2 == null) {
                        s2 = "0";
                    }
                    return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
                })
                .forEach(System.out::println);

    }

    private void aaa(@NonNull() Integer i) {
        System.out.println("ddd");
    }

    @Test
    public void test11() {
        //这个list的重复数据返回，只需要返回一个就可以了
        List<Integer> list = List.of(1, 2, 4, 2);
        List<Integer> tempList = new ArrayList<>();
        for (Integer temp : list) {
            if (tempList.contains(temp)) {
                System.out.println(temp);
            }else {
                tempList.add(temp);
            }
        }
    }

    @Test
    public void test22() {
        String s = "465.00500";
        Object bigDecimal = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP).toString();
        System.out.println("format = " + bigDecimal);
    }


    @Test
    public void test24() {
        List<Integer> list = digui(10);
        System.out.println("list = " + list);
    }

    private final List<Integer> dd = new ArrayList<>();
    private List<Integer> digui(int i) {
        if (i == 1) {
            return dd;
        }
        dd.add(i);
       return digui(i - 1);
    }

    @Test
    public void test25() {
        Integer i = null;
        boolean b = 1 == i;
        System.out.println("b = " + b);
    }
}
