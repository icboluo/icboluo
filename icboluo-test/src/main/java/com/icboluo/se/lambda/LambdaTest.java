package com.icboluo.se.lambda;

import com.icboluo.dataobject.Student;
import com.icboluo.se.lambda.predicatecom.FilterStudentByAge;
import com.icboluo.se.lambda.predicatecom.FilterStudentByName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author lp
 */
public class LambdaTest {
    List<Student> list = Arrays.asList(
            new Student(22, "one"),
            new Student(2, "two"),
            new Student(66, "three"),
            new Student(4, "three")
    );

    /**
     * 策略设计模式：多个类实现一个接口，调用接口中的抽象方法的时候执行的是实现类中的方法
     * 造成的问题：需要创建类去实现接口0·0
     */
    @Test
    public void test1() {
        List<Student> students1 = filterStudent(list, new FilterStudentByAge());
        System.out.println("students1 = " + students1);
        List<Student> students2 = filterStudent(list, new FilterStudentByName());
        System.out.println("students2 = " + students2);
    }

    /**
     * 匿名内部类
     */
    @Test
    public void test2() {
        List<Student> students = filterStudent(list, new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getAge() > 55;
            }
        });
        System.out.println("students = " + students);
    }

    /**
     * lambda表达式
     */
    @Test
    public void test3() {
        //箭头左侧放的是参数列表，右侧放的是接口中抽象方法的实现
        List<Student> students = filterStudent(list, s -> s.getAge() > 55);
        students.forEach(System.out::println);
    }

    /**
     * streamAPI
     */
    @Test
    public void test4() {
        list.stream()
                .filter((s) -> s.getAge() > 55)
                .forEach(System.out::println);
    }

    private List<Student> filterStudent(List<Student> list, Predicate<Student> s) {
        List<Student> students = new ArrayList<>();
        for (Student student : list) {
            if (s.test(student)) {
                students.add(student);
            }
        }
        return students;
    }
}
