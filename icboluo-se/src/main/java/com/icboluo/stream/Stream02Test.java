package com.icboluo.stream;


import com.icboluo.object.StatusStudent;
import com.icboluo.util.IcBoLuoException;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 不会储存元素，不会改变源对象
 * Stream 操作步骤：
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作
 *
 * @author lp
 */
public class Stream02Test {

    List<StatusStudent> stuList = Arrays.asList(
            new StatusStudent(1, "one", StatusStudent.Status.BUSY),
            new StatusStudent(2, "two", StatusStudent.Status.FREE),
            new StatusStudent(3, "three", StatusStudent.Status.VOCATION),
            new StatusStudent(4, "four", StatusStudent.Status.BUSY),
            new StatusStudent(5, "five", StatusStudent.Status.FREE)
    );

    /**
     * 创建Stream
     * 1.可以通过Collection 系列集合提供的.stream（串行流）和.parallelStream（并行流）
     * 2.通过Arrays中的静态方法stream（）获取数组流
     * 3.通过Stream中的静态方法of（）
     * 4.创建无限流
     */
    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        StatusStudent[] strArr = new StatusStudent[10];
        Stream<StatusStudent> stream2 = Arrays.stream(strArr);

        Stream<String> stream3 = Stream.of("a", "b", "c");

        //4.1迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        stream4.limit(100).forEach(System.out::println);
        //4.2生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(100).forEach(System.out::println);

        IntStream.range(0, 10);
    }

    /**
     * 中间操作:多个中间操作可以连成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何处理
     * 而在终止操作时一次性全部处理，称为‘惰性求值’
     * filter：接受lambda，从流中排除某些元素，过滤之后如果是空，任然可以搜集起来(收集到容器中)
     */
    @Test
    public void test2() {
        // 内部迭代：由stream api 完成
        Stream<StatusStudent> stream1 = stuList.stream()
                .filter(s -> {
                    System.out.println("stream api 的中间操作没有终止操作不执行");
                    return s.getAge() > 2;
                });
        stream1.forEach(System.out::println);
    }

    /**
     * limit：使元素不超过给定数量
     * skip（n）:返回一个扔掉了前n个元素的流，若原流中元素不足n个。返回一个空流，与limit互补
     * distinct:通过流生成元素的hashCode和equals(流中的对象需要重写hashCode和equals才能去重)去除重复元素
     */
    @Test
    public void test3() {
        stuList.stream()
                .filter(s -> {
                    // 在整体上游有路效果；（局部无法短路，这个不能实现
                    System.out.println(s.getAge() + " 短路，可以提高效率，找到满足条件的元素，剩下的不再迭代");//&& ||
                    return s.getAge() > 1;
                })
                .limit(2)
                .forEach(System.out::println);
        System.out.println("-------------------------------------------------------------");
        stuList.stream()
                .filter(s -> s.getAge() > 1)
                .skip(2)
                .distinct()
                .forEach(System.out::print);
    }

    /**
     * map映射：接受lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数，
     * 该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap：接受一个函数作为参数，将流中的每个值转换成一个流，将所有的流连成一个流
     * peek和map差不多，map有返回值，peek无返回值，peek常用做对象本身修改属性操作
     */
    @Test
    public void test4() {
        List<String> list = List.of("aa", "bb", "cc", "dd", "ee");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::print);
        System.out.println("---------------------------------");
        stuList.stream()
                .map(StatusStudent::getName)
                .forEach(System.out::print);
        System.out.println("---------------------------------");
        //map本身得到的是一个新流
        Stream<Stream<Character>> stream1 = list.stream()
                .map(Stream02Test::splitCharacter1);
        //{{a,a},{b,b},{c,c}}
        stream1.forEach(sm -> {
            sm.forEach(System.out::print);
        });
        System.out.println("---------------------------------");
        Stream<Character> stream2 = list.stream()
                .flatMap(Stream02Test::splitCharacter1);
        //{{a,a,b,b,c,c}}
        stream2.forEach(System.out::print);
    }

    /**
     * sorted()自然排序（Comparable）
     * sorted（Comparator com）定制排序
     */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee");
        list.stream()
                .sorted()
                .forEach(System.out::print);
        System.out.println("--------------------------");
//        排序：先按年龄比，相同按姓名比
        stuList.stream()
                .sorted((s1, s2) -> {
                    if (s1.getAge() == s2.getAge()) {
                        return s1.getName().compareTo(s2.getName());
                    } else {
                        return -Integer.compare(s1.getAge(), s2.getAge());
                    }
                }).forEach(System.out::println);

        stuList.stream()
                .sorted((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()))
                //.sorted(Comparator.comparingInt(Student::getAge))
                .forEach(System.out::println);

        //过滤，获得名称，排序
        stuList.stream()
                .filter(s -> s.getStatus().equals(StatusStudent.Status.FREE))
                .map(StatusStudent::getName)
                .sorted((s1, s2) -> s1.compareTo(s2))
                .forEach(System.out::print);
        System.out.println();
        //按姓名排序name
        stuList.stream()
                .map(StatusStudent::getName)
                .sorted()
                .forEach(System.out::print);
        System.out.println();
        String str = stuList.stream()
                .map(StatusStudent::getName)
                .sorted()
                .reduce("", String::concat);
        System.out.println("str = " + str);
        //打乱名字中的字母排序 区分大小写/不区分大小写
        stuList.stream()
                .map(StatusStudent::getName)
                .flatMap(Stream02Test::splitCharacter1)
                .sorted()
                .forEach(System.out::print);
        System.out.println();
        stuList.stream()
                .map(StatusStudent::getName)
                .flatMap(Stream02Test::splitCharacter2)
                .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                //.sorted(String::compareToIgnoreCase)
                .forEach(System.out::print);
    }

    /**
     * stream api 的终止操作
     * allMatch 检查是否匹配所有元素
     * anyMatch 检查是否匹配至少一个元素
     * noneMatch 检查是否没有匹配所有元素
     * findFirs 返回当前流中的第一个元素
     * findAny 返回当前流中的任意一个元素
     * count、max、min
     */
    @Test
    public void test6() {
        boolean b1 = stuList.stream()
                .allMatch(s -> s.getStatus().equals(StatusStudent.Status.BUSY));
        System.out.println("b1 = " + b1);
        boolean b2 = stuList.stream()
                .anyMatch(s -> s.getStatus().equals(StatusStudent.Status.VOCATION));
        System.out.println("b2 = " + b2);
        boolean b3 = stuList.stream()
                .noneMatch(s -> s.getStatus().equals(StatusStudent.Status.VOCATION));
        System.out.println("b3 = " + b3);
        //返回的结果集有可能为null时，stream会将这个值封装到optional容器中
        Optional<StatusStudent> op1 = stuList.stream()
                .sorted((s1, s2) -> -Integer.compare(s1.getAge(), s2.getAge()))
                .findFirst();
        System.out.println(op1.get());
        Optional<StatusStudent> op2 = stuList.stream()
                .filter(s -> s.getStatus().equals(StatusStudent.Status.FREE))
                .findAny();
//        如果是null，则抛出异常
        System.out.println(op2.orElseThrow(IcBoLuoException::new));

        long count = stuList.stream()
                .count();
        System.out.println("count = " + count);
        Optional<StatusStudent> op3 = stuList.stream()
                .max((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()));
        System.out.println(op3.get());
        Optional<Integer> op4 = stuList.stream()
                .map(StatusStudent::getAge)
                .min(Integer::compare);
        System.out.println(op4.get());
    }

    /**
     * reduce(identity<起始值，binaryOperator<二元运算) 规约，可以将流中的元素反复结合起来，得到一个值
     */
    @Test
    public void test7() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //将起始值作为x+第一个元素y，得到的值作为新的起始值x，再加第二个元素y... ...
        Integer reduce = list.stream()
                .reduce(0, Integer::sum);
        System.out.println("reduce = " + reduce);
        //上面的有起始值，所以比不为null，下面的可能为null，所以放在optional中
        Optional<Integer> op = stuList.stream()
                .map(StatusStudent::getAge)
                .reduce(Integer::sum);
        System.out.println(op.orElse(0));

//      指定元素列表求平方
        Integer[] ints = new Integer[]{1, 2, 3, 4, 5};
        Arrays.stream(ints)
                .map(x -> x * x)
                .forEach(System.out::println);
    }

    /**
     * 收集
     */
    @Test
    public void test8() {
        List<String> list = stuList.stream()
                .map(StatusStudent::getName)
                .toList();
        list.forEach(System.out::println);
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        Set<StatusStudent.Status> set = stuList.stream()
                .map(StatusStudent::getStatus)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        HashSet<String> hs = stuList.stream()
                .map(StatusStudent::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);

    }

    /**
     * 汇总收集
     */
    @Test
    public void test9() {
        Long count = stuList.stream()
                .collect(Collectors.counting());
        System.out.println("count = " + count);
        Double avg = stuList.stream()
                .collect(Collectors.averagingInt(StatusStudent::getAge));
        System.out.println("avg = " + avg);
        Integer sum = stuList.stream()
                .collect(Collectors.summingInt(StatusStudent::getAge));
        System.out.println("sum = " + sum);
        Optional<StatusStudent> max = stuList.stream()
                .collect(Collectors.maxBy((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge())));
        System.out.println(max.get());
        Optional<Integer> min = stuList.stream()
                .map(StatusStudent::getAge)
                .collect(Collectors.minBy(Integer::compare));
        System.out.println(min.get());
//        汇总一起收集
        IntSummaryStatistics iss = stuList.stream()
                .collect(Collectors.summarizingInt(StatusStudent::getAge));
        System.out.println("iss.getAverage() = " + iss.getAverage());
        System.out.println("iss.getCount() = " + iss.getCount());
        System.out.println("iss.getMax() = " + iss.getMax());
        System.out.println("iss.getMin() = " + iss.getMin());
        System.out.println("iss.getSum() = " + iss.getSum());
    }

    /**
     * 分组
     * 多级分组
     */
    @Test
    public void test10() {
        Map<StatusStudent.Status, List<StatusStudent>> map1 = stuList.stream()
                .collect(Collectors.groupingBy(StatusStudent::getStatus));

        Map<StatusStudent, List<StatusStudent>> map2 = stuList.stream()
                .collect(Collectors.groupingBy(s -> new StatusStudent(s.getAge(), s.getName())));
        System.out.println("map1 = " + map1);
        Map<StatusStudent.Status, Map<String, List<StatusStudent>>> map3 = stuList.stream()
                .collect(Collectors.groupingBy(StatusStudent::getStatus, Collectors.groupingBy(s -> {
                    if (s.getAge() > 2) {
                        return "大于2";
                    } else if (s.getAge() < 4) {
                        return "小于4";
                    } else {
                        return "其他";
                    }
                })));
        System.out.println("map3 = " + map3);
    }

    /**
     * 分区:true:;false:;
     */
    @Test
    public void test11() {
        Map<Boolean, List<StatusStudent>> map = stuList.stream()
                .collect(Collectors.partitioningBy(s -> s.getAge() > 2));
        System.out.println("map = " + map);
    }

    /**
     * 连接
     */
    @Test
    public void test12() {
        String str = stuList.stream()
                .map(StatusStudent::getName)
                .collect(Collectors.joining(",", "<", ">"));
        System.out.println("str = " + str);
    }

    private static Stream<Character> splitCharacter1(String str) {
        List<Character> list = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    private static Stream<String> splitCharacter2(String str) {
        List<String> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }
}
