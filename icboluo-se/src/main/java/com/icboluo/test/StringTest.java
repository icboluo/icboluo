package com.icboluo.test;

import com.icboluo.object.StatusStudent;
import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StringTest {
    List<StatusStudent> stus = Arrays.asList(
            new StatusStudent(1, "one", StatusStudent.Status.BUSY),
            new StatusStudent(2, "two", StatusStudent.Status.FREE),
            new StatusStudent(3, "three", StatusStudent.Status.VOCATION),
            new StatusStudent(4, "four", StatusStudent.Status.BUSY),
            new StatusStudent(5, "five", StatusStudent.Status.FREE),
            new StatusStudent(5, "five", StatusStudent.Status.FREE),
            new StatusStudent(5, "five", StatusStudent.Status.FREE)
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
    public void test16() {
        BigDecimal bd = new BigDecimal("52.79999923706055");
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(bd);
        System.out.println("format = " + format);
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
            } else {
                tempList.add(temp);
            }
        }
    }

    @Test
    public void test22() {
        String s = "465.00500";
        Object bigDecimal = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP).toString();
        System.out.println(STR."format = \{bigDecimal}");
    }

    @Test
    public void test25() {
        Integer i = null;
        boolean b = 1 == i;
        System.out.println(STR."b = \{b}");
    }

    @Test
    public void test26() {
        String name = "xiao wang";
        String msg = STR."name is : \{name}";
        System.out.println("msg = " + msg);

        String email = STR."""
            Hello Mr \{name}
                good morning;
                            ByBy""";
        System.out.println(email);
    }
}
