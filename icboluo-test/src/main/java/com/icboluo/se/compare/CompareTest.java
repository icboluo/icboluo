package com.icboluo.se.compare;


import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author icboluo
 * @date 2020-08-07 16:07
 */
public class CompareTest {
    private final List<Student> stus = Arrays.asList(
            new Student(1),
            new Student(4),
            new Student(5),
            new Student(3)
    );

    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 4, 5, 3);
        //传入的不是接口，是接口的实现类对象，也就是自己写的接口的实现，实现类的最大范围已经在list中定义好了
        list.sort(new MyComparator());
        System.out.println("list = " + list);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "acd", "acac", "bde", "ffe");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                                /*
                自然排序 :
                    o1.compareTo(o2);   字典顺序  a~z
                    o2.compareTo(o1);   字典顺序  z~a

                自定义排序 : 长度
                    o1.length() - o2.length();  从短到长
                    o2.length() - o1.length();  从长到短

                说明 : 如果返回 0, 表示没有任何变化. 顺序不变.
                 */
                int i = o1.compareTo(o2);
                if (i == 0) {

                }
                return 0;
            }
        });
    }

    @Test
    public void test3() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 4, 5, 3);
        list.sort((o1, o2) -> 0);
    }

    /**
     * 这种写法是匿名内部类的实现，无需让student实现compare接口
     */
    @Test
    public void test4() {
        stus.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return 0;
            }
        });

    }
}
