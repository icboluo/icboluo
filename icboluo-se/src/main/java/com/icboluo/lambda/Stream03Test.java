package com.icboluo.lambda;

import com.icboluo.object.Student;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author icboluo
 * @since 2020/11/6 19:49
 */
public class Stream03Test {

    List<Student> stuList1 = Arrays.asList(
            new Student(1, "one", Student.Status.BUSY),
            new Student(2, "two", Student.Status.FREE),
            new Student(3, "three", Student.Status.VOCATION)
    );
    List<Student> stuList2 = Arrays.asList(
            new Student(4, "four", Student.Status.BUSY),
            new Student(5, "five", Student.Status.FREE)
    );

    /**
     * 将多个list的数据加到一起
     */
    @Test
    public void test01() {
        List<List<Student>> list = new ArrayList<>();
        list.add(stuList1);
        list.add(stuList2);
        list.stream().flatMap(Collection::stream).forEach(System.out::println);

        Stream.of(stuList1.stream(), stuList2.stream()).flatMap(ele -> ele).toList();
    }

    /**
     * 根据name去重,可是根据name去重之后，结果是否有些不确定，此方法很少使用
     */
    @Test
    public void test2() {
        List<Student> distinctList = stuList1.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(
                        // 利用 TreeSet 的排序去重构造函数来达到去重元素的目的
                        () -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new));
        System.out.println("distinctList = " + distinctList);
    }

    /**
     * 易错点
     *
     * @see com.icboluo.util.BeanHelper mapConvert
     */
    @Test
    public void test3() {
        stuList1.stream()
                .anyMatch(stu -> stu.getAge() == 2);
        // 请注意 the stream is empty return true
        stuList1.stream()
                .allMatch(stu -> stu.getAge() == 2);

        // Stream中ele不能为null；map key不能重复、不能为null；val不能为null
        stuList1.stream()
                .collect(Collectors.toMap(Student::getName, Student::getAge));
    }

    /**
     * 收集成数组，常用于算法题目中
     */
    @Test
    public void test4() {
        int[] arr1 = stuList1.stream()
                .mapToInt(Student::getAge)
                .toArray();
        Integer[] arr2 = stuList1.stream()
                .map(Student::getAge)
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
}
