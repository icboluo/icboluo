package com.icboluo.stream;

import com.icboluo.object.Student;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author icboluo
 * @since 2020/11/6 19:49
 */
public class Stream03Test {

    List<Student> stuList1 = Arrays.asList(new Student(1, "one", Student.Status.BUSY), new Student(2, "two", Student.Status.FREE), new Student(3, "three", Student.Status.VOCATION));
    List<Student> stuList2 = Arrays.asList(new Student(4, "four", Student.Status.BUSY), new Student(5, "five", Student.Status.FREE));

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
        List<Student> distinctList = stuList1.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                // 利用 TreeSet 的排序去重构造函数来达到去重元素的目的
                () -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new));
        System.out.println("distinctList = " + distinctList);

        stuList1.stream().filter(distinctByKey(Student::getName)).forEach(System.out::println);
    }

    /**
     * 根据对象属性进行去重
     * 可以这样理解后一句话，filter需要的是接口，接口的实现类是return语句，而不是new map,所以只有return语句需要执行多次
     * 表示一个方法中执行的内容也可以被割裂调用的；Stream在启动的时候，先调用filter函数，调用里面的方法，运行过程中执行函数式接口
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>();
        System.out.println("map被创建了");
        return t -> {
            System.out.println("函数式接口执行了");
            return map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
        };
    }

    /**
     * 易错点
     *
     * @see com.icboluo.util.BeanHelper mapConvert
     */
    @Test
    public void test3() {
        stuList1.stream().anyMatch(stu -> stu.getAge() == 2);
        // 请注意 the stream is empty return true
        stuList1.stream().allMatch(stu -> stu.getAge() == 2);

        // Stream中ele不能为null；map key不能重复、不能为null；val不能为null
        stuList1.stream().collect(Collectors.toMap(Student::getName, Student::getAge));
        // 第三个参数标注key重复情况下的处理逻辑，第四个参数是说map的结构，常用 LinkedHashMap 做排序map处理
        stuList1.stream().collect(Collectors.toMap(Student::getName, Student::getAge, (k1, k2) -> k2, LinkedHashMap::new));
    }

    /**
     * 收集成数组，常用于算法题目中
     */
    @Test
    public void test4() {
        int[] arr1 = stuList1.stream().mapToInt(Student::getAge).toArray();
        Integer[] arr2 = stuList1.stream().map(Student::getAge).toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        List<Integer> list = stuList1.stream().mapToInt(Student::getAge)
                // 装箱，常用于IntStream无法排序的情况
                .boxed().sorted((a, b) -> b - a).toList();
        System.out.println(list);
    }

    /**
     * Optional的长链式调用不会出错NPE（map操作返回的是ofNullAble）
     */
    @Test
    public void test5() {
        Optional.ofNullable(stuList1)
                .map(o -> o.get(0))
                .map(Student::getAge)
                .filter(a -> a > 100000)
                .map(StringBuffer::new)
                .map(StringBuffer::toString)
                .ifPresent(System.out::println);
    }
}
