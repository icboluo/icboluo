package com.icboluo.stream;

import com.icboluo.stream.predicatecom.FilterStudentByAge;
import com.icboluo.stream.predicatecom.FilterStudentByName;
import com.icboluo.object.StatusStudent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>前言：为什么要使用Lambda和Stream
 * <p>针对于多个学生而言，我们需要做一个过滤函数，选中自己需要的学生
 * <p>首先，过滤函数就要具有通用性，我们先做一个私有函数做公共逻辑处理
 *
 * @author icboluo
 * @see ForewordTest#filterStudent(List, Predicate)
 */
public class ForewordTest {
    /**
     * 学生容器
     */
    List<StatusStudent> stuList = Arrays.asList(
            new StatusStudent(22, "one"),
            new StatusStudent(2, "two"),
            new StatusStudent(66, "three"),
            new StatusStudent(4, "three")
    );

    /**
     * 策略设计模式：多个类实现一个接口，调用接口中的抽象方法的时候执行的是实现类中的方法
     * 造成的问题：需要创建类去实现接口0·0
     *
     * @see FilterStudentByAge
     * @see FilterStudentByName
     */
    @Test
    public void test1() {
        List<StatusStudent> students1 = filterStudent(stuList, new FilterStudentByAge());
        System.out.println("students1 = " + students1);
        List<StatusStudent> students2 = filterStudent(stuList, new FilterStudentByName());
        System.out.println("students2 = " + students2);
    }

    /**
     * 匿名内部类：不定义公共类，定义临时类
     */
    @Test
    public void test2() {
        List<StatusStudent> students = filterStudent(stuList, new Predicate<StatusStudent>() {
            @Override
            public boolean test(StatusStudent student) {
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
        List<StatusStudent> students = filterStudent(stuList, s -> s.getAge() > 55);
        students.forEach(System.out::println);
    }

    /**
     * Stream api
     *
     * @see java.util.stream.Stream
     */
    @Test
    public void test4() {
        stuList.stream()
                .filter(s -> s.getAge() > 55)
                .forEach(System.out::println);
    }

    private List<StatusStudent> filterStudent(List<StatusStudent> list, Predicate<StatusStudent> pre) {
        List<StatusStudent> ans = new ArrayList<>();
        for (StatusStudent student : list) {
            if (pre.test(student)) {
                ans.add(student);
            }
        }
        return ans;
    }
}
