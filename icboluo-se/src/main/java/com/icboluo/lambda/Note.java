package com.icboluo.lambda;

import com.icboluo.object.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author icboluo
 * @since 2020/11/6 19:49
 */
public class Note {
    /**
     * 将多个list的数据加到一起
     */
    @Test
    public void test01() {
        List<Student> stus1 = Arrays.asList(
                new Student(1, "one", Student.Status.BUSY),
                new Student(2, "two", Student.Status.FREE),
                new Student(3, "three", Student.Status.VOCATION)
        );
        List<Student> stus2 = Arrays.asList(
                new Student(4, "four", Student.Status.BUSY),
                new Student(5, "five", Student.Status.FREE)
        );
        List<List<Student>> list = new ArrayList<>();
        list.add(stus1);
        list.add(stus2);
        list.stream().flatMap(Collection::stream).forEach(System.out::println);
    }
}
