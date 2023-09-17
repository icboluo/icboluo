package com.icboluo.stream;

import com.icboluo.object.StatusStudent;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author icboluo
 * @since 2020/11/6 19:49
 */
public class Stream03Test {

    List<StatusStudent> stuList1 = Arrays.asList(
            new StatusStudent(1, "one", StatusStudent.Status.BUSY),
            new StatusStudent(2, "two", StatusStudent.Status.FREE),
            new StatusStudent(3, "three", StatusStudent.Status.VOCATION));

    List<StatusStudent> stuList2 = Arrays.asList(
            new StatusStudent(4, "four", StatusStudent.Status.BUSY),
            new StatusStudent(5, "five", StatusStudent.Status.FREE));

    /**
     * 将多个list的数据加到一起
     */
    @Test
    public void test01() {
        List<List<StatusStudent>> list = new ArrayList<>();
        list.add(stuList1);
        list.add(stuList2);
        list.stream().flatMap(Collection::stream).forEach(System.out::println);

        Stream.of(stuList1.stream(), stuList2.stream()).flatMap(ele -> ele).toList();
        Stream.of(stuList1, stuList2).flatMap(Collection::stream).toList();
    }

    /**
     * 根据name去重,可是根据name去重之后，结果是否有些不确定，此方法很少使用
     */
    @Test
    public void test2() {
        List<StatusStudent> distinctList = stuList1.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                // 利用 TreeSet 的排序去重构造函数来达到去重元素的目的
                () -> new TreeSet<>(Comparator.comparing(StatusStudent::getName))), ArrayList::new));
        System.out.println("distinctList = " + distinctList);

        stuList1.stream().filter(distinctByKey(StatusStudent::getName)).forEach(System.out::println);
    }

    /**
     * 根据对象属性进行去重
     * 可以这样理解后一句话，filter需要的是接口，接口的实现类是return语句，而不是new map,所以只有return语句需要执行多次
     * 表示一个方法中执行的内容也可以被割裂调用的；Stream在启动的时候，先调用filter函数，调用里面的方法，运行过程中执行函数式接口
     *
     * @param keyExtractor 需要过滤的元素
     * @param <T>          实体类型
     * @return 断言型函数式接口
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
     * @see com.icboluo.util.BeanHelper#mapConvert(Map, Function, Function)
     */
    @SuppressWarnings("all")
    @Test
    public void test3() {
        stuList1.stream().anyMatch(stu -> stu.getAge() == 2);
        // 请注意 the stream is empty return true
        stuList1.stream().allMatch(stu -> stu.getAge() == 2);

        // Stream中ele不能为null；map key不能重复、不能为null；val不能为null
        stuList1.stream().collect(Collectors.toMap(StatusStudent::getName, StatusStudent::getAge));
        // 第三个参数标注key重复情况下的处理逻辑，第四个参数是说map的结构，常用 LinkedHashMap 做排序map处理
        stuList1.stream().collect(Collectors.toMap(StatusStudent::getName, StatusStudent::getAge, (k1, k2) -> k2, LinkedHashMap::new));
    }

    /**
     * 收集成数组，常用于算法题目中
     */
    @Test
    public void test4() {
        int[] arr1 = stuList1.stream().mapToInt(StatusStudent::getAge).toArray();
        Integer[] arr2 = stuList1.stream().map(StatusStudent::getAge).toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        List<Integer> list = stuList1.stream().mapToInt(StatusStudent::getAge)
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
                .map(StatusStudent::getAge)
                .filter(a -> a > 100000)
                .map(StringBuffer::new)
                .map(StringBuffer::toString)
                .ifPresent(System.out::println);
    }

    /**
     * 求元素出现次数
     */
    @Test
    public void test6() {
        Map<Integer, Integer> ageCountMap = stuList1.stream()
                .collect(Collectors.groupingBy(StatusStudent::getAge, Collectors.summingInt(ele -> 1)));
        System.out.println("ageCountMap = " + ageCountMap);

        // toMap 第4个参数用linked可以保证map的顺序
        LinkedHashMap<Integer, Integer> collect = stuList1.stream()
                .collect(Collectors.toMap(StatusStudent::getAge, ele -> 1, Integer::sum, LinkedHashMap::new));
        System.out.println("collect = " + collect);
    }

    @Test
    public void test7() {
        Msg msg1 = new Msg(Arrays.asList("正确的1", "correct1"), Arrays.asList("错误的1", "error1"));
        Msg msg2 = new Msg(Arrays.asList("正确的2", "correct2"), Arrays.asList("错误的2", "error2"));
        List<Msg> list = Arrays.asList(msg1, msg2);
        // 求所有的消息结果
        // 方法1确定：对于每一个msg，均创建了一个中间的set；对于每一个msg，它的correct和error均遍历2遍（addAll，收集
        Set<String> set1 = list.stream().flatMap(msg -> {
            HashSet<String> set = new HashSet<>();
            set.addAll(msg.correct);
            set.addAll(msg.error);
            return set.stream();
        }).collect(Collectors.toSet());
        System.out.println(set1);

        // optimize 新写法
        Set<String> set2 = list.stream().<String>mapMulti((msg, consumer) -> {
            msg.correct.forEach(consumer);
            msg.error.forEach(consumer);
            // msgService.findErrorById(msg.getId).forEach(consumer)
        }).collect(Collectors.toSet());
        System.out.println(set2);
    }

    /**
     * 将包含迭代器的混合类型，拆分成同一个类型
     */
    @Test
    public void test8() {
        // 嵌套列表
        var nestedList = List.of(1, List.of(2, List.of(3, 4)), 5);
        Stream<Object> expandStream = nestedList.stream().mapMulti(Stream03Test::expandIterator);
        System.out.println(expandStream.toList());

        // 从stream中依次获取满足条件的元素，直到不满足条件为止结束获取
        IntStream.of(12, 4, 3, 6, 8, 9).takeWhile(x -> x % 2 == 0).forEach(System.out::println);
        // 从stream中依次删除满足条件的元素，这2个函数会提前终止，和filter不一样
        IntStream.of(12, 4, 3, 6, 8, 9).dropWhile(x -> x % 2 == 0).forEach(System.out::println);
    }

    /**
     * 展开迭代
     *
     * @param obj
     * @param consumer
     */
    public static void expandIterator(Object obj, Consumer<Object> consumer) {
        if (obj instanceof Iterable<?> elements) {
            for (Object one : elements) {
                expandIterator(one, consumer);
            }
        } else if (obj != null) {
            consumer.accept(obj);
        }
    }

    public record Msg(List<String> correct, List<String> error) {
    }

}
