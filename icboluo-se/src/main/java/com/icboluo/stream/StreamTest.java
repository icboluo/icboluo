package com.icboluo.stream;

import com.icboluo.object.StatusStudent;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Stream 练习题
 *
 * @author icboluo
 * @since 2023-03-12 12:53
 */
public class StreamTest {
    List<StatusStudent> stuList = Arrays.asList(
            new StatusStudent(1, "one", StatusStudent.Status.BUSY),
            new StatusStudent(2, "two", StatusStudent.Status.FREE),
            new StatusStudent(3, "three", StatusStudent.Status.VOCATION),
            new StatusStudent(4, "four", StatusStudent.Status.BUSY),
            new StatusStudent(5, "five", StatusStudent.Status.FREE)
    );

    /**
     * 获取所有的学生名称
     * TODO 测试方法，下一个方法书写完整之后，可以运行次方法进行测试
     */
    @Test
    public void test01() {
        System.out.println(getAllStuName());
    }

    public List<String> getAllStuName() {
        // TODO 待实现的方法，请删除下一行代码，自行书写
        return null;
    }

    /**
     * 获取忙碌学生的个数
     */
    @Test
    public void test02() {
        System.out.println(busyStuCount());
    }

    public Integer busyStuCount() {
        return null;
    }

    /**
     * 求学生的状态一共有几种
     */
    @Test
    public void test03() {
        System.out.println(countStatus());
    }

    public Integer countStatus() {
        return null;
    }

    /**
     * 选择排名前3的学生
     */
    @Test
    public void test04() {
        System.out.println(limit3());
    }

    public List<StatusStudent> limit3() {
        return null;
    }

    /**
     * 将所有的学生名称大写
     */
    @Test
    public void test05() {
        System.out.println(toUpperCase());
    }

    public List<String> toUpperCase() {
        return null;
    }

    /**
     * 将学生按照年龄由大到小排序
     */
    @Test
    public void test06() {
        System.out.println(sort());
    }

    public List<StatusStudent> sort() {
        return null;
    }

    /**
     * 求学生名字中包含哪些字母（需去重
     */
    @Test
    public void test07() {
        System.out.println(containChar());
    }

    public List<Character> containChar() {
        return null;
    }

    /**
     * 求学生年龄的最小值，最大值，总和(按照顺序放到数组中
     */
    @Test
    public void test08() {
        System.out.println(ageMaxAndMin());
    }

    public int[] ageMaxAndMin() {
        return null;
    }

    /**
     * 判断是否有一个学生年龄小于3
     */
    @Test
    public void test09() {
        System.out.println(ageIsSmall3());
    }

    public Boolean ageIsSmall3() {
        return null;
    }

    /**
     * 查找集合中第一个状态是 FREE 的学生
     */
    @Test
    public void test10() {
        System.out.println(findFirstStatusIsFree());
    }

    public Optional<StatusStudent> findFirstStatusIsFree() {
        return null;
    }

    /**
     * 求所有的年龄乘积
     */
    @Test
    public void test11() {
        System.out.println(ageMultiply());
    }

    public Integer ageMultiply() {
        return null;
    }

    /**
     * 将学生按照状态进行分组
     */
    @Test
    public void test12() {
        System.out.println(groupByStatus());
    }

    public Map<StatusStudent.Status, List<StatusStudent>> groupByStatus() {
        return null;
    }

    /**
     * 将学生按照年龄是否大于3分区，如果大于3，表示true
     */
    @Test
    public void test13() {
        System.out.println(groupByAge());
    }

    public Map<Boolean, List<StatusStudent>> groupByAge() {
        return null;
    }

    /**
     * 将学生名称进行拼接，用;隔开（英文分号
     */
    @Test
    public void test14() {
        System.out.println(joinName());
    }

    public String joinName() {
        return null;
    }

    /**
     * 先按照状态分组，再按照name分组
     */
    @Test
    public void test15() {
        System.out.println(groupByStatusName());
    }

    public Map<StatusStudent.Status, Map<String, List<StatusStudent>>> groupByStatusName() {
        return null;
    }

    /**
     * 求学生名称出现的次数，比如four出现了1次...
     */
    @Test
    public void test16() {
        System.out.println(eleCountMap());
    }

    public Map<String, Integer> eleCountMap() {
        return null;
    }

    /**
     * 获取年龄大于3，然后再从中获取状态为 BUSY 的学生，
     * 再从中获取名称首字母为f
     * 再从中获取学生的名称然后大写
     * 再返回查找到的第一个，如果没有这样的数据，返回 "NOT HAVE THIS DATA"
     */
    @Test
    public void test18() {
        System.out.println(getLinkedMethod());
    }

    public String getLinkedMethod() {
        // 请使用该list
        List<StatusStudent> list = new ArrayList<>(stuList);
        list.add(new StatusStudent(4, null, StatusStudent.Status.BUSY));
        return null;
    }


    /**
     * TODO 总测试，写上一部分待实现的方法之后，可以运行总测试，判断结果是否正确
     */
    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        StreamTest streamTest = new StreamTest();
        Class<StreamTest> cla = StreamTest.class;
        Method[] methods = cla.getMethods();
        List<Method> testMethods = Arrays.stream(methods)
                .filter(method -> !method.getName().startsWith("test") && !method.getName().endsWith("Ans"))
                .toList();
        for (Method testMethod : testMethods) {
            Method ansMethod;
            try {
                ansMethod = cla.getMethod(testMethod.getName() + "Ans");
            } catch (NoSuchMethodException e) {
                continue;
            }
            Object testInvoke = testMethod.invoke(streamTest);
            if (testInvoke != null) {
                System.out.println("运行结果为: " + testInvoke);
                Object ansInvoke = ansMethod.invoke(streamTest);
                if (ObjectUtils.nullSafeEquals(testInvoke, ansInvoke)) {
                    System.out.println("结果正确");
                } else {
                    System.out.println("结果错误，期望结果为: " + ansInvoke);
                }
            }
        }
    }

    public List<String> getAllStuNameAns() {
        return stuList.stream().map(StatusStudent::getName).toList();
    }

    public Integer busyStuCountAns() {
        return (int) stuList.stream().filter(stu -> StatusStudent.Status.BUSY.equals(stu.getStatus())).count();
    }

    public Integer countStatusAns() {
        return (int) stuList.stream().map(StatusStudent::getStatus).distinct().count();
    }

    public List<StatusStudent> limit3Ans() {
        return stuList.stream().limit(3).toList();
    }

    public List<String> toUpperCaseAns() {
        return stuList.stream().map(StatusStudent::getName).map(String::toUpperCase).toList();
    }

    public List<StatusStudent> sortAns() {
        return stuList.stream().sorted((a, b) -> b.getAge() - a.getAge()).toList();
    }

    public List<Character> containCharAns() {
        return stuList.stream()
                .map(StatusStudent::getName)
                .map(str -> IntStream.range(0, str.length()).mapToObj(str::charAt).toList())
                .flatMap(Collection::stream)
                .distinct()
                .toList();
    }

    public int[] ageMaxAndMinAns() {
        int[] arr = new int[3];
        IntSummaryStatistics summary = stuList.stream().collect(Collectors.summarizingInt(StatusStudent::getAge));
        arr[0] = summary.getMin();
        arr[1] = summary.getMax();
        arr[2] = (int) summary.getCount();
        return arr;
    }

    public Boolean ageIsSmall3Ans() {
        return stuList.stream().anyMatch(stu -> stu.getAge() < 3);
    }

    public Optional<StatusStudent> findFirstStatusIsFreeAns() {
        return stuList.stream().filter(stu -> stu.getStatus().equals(StatusStudent.Status.FREE)).findFirst();
    }

    public Integer ageMultiplyAns() {
        return stuList.stream().map(StatusStudent::getAge).reduce(1, (a, b) -> a * b);
    }

    public Map<StatusStudent.Status, List<StatusStudent>> groupByStatusAns() {
        return stuList.stream().collect(Collectors.groupingBy(StatusStudent::getStatus));
    }

    public Map<Boolean, List<StatusStudent>> groupByAgeAns() {
        return stuList.stream().collect(Collectors.partitioningBy(stu -> stu.getAge() > 3));
    }

    public String joinNameAns() {
        return stuList.stream().map(StatusStudent::getName).collect(Collectors.joining(";"));
    }

    public Map<StatusStudent.Status, Map<String, List<StatusStudent>>> groupByStatusNameAns() {
        return stuList.stream()
                .collect(Collectors.groupingBy(StatusStudent::getStatus,Collectors.groupingBy(StatusStudent::getName)));
    }

    public Map<String, Integer> eleCountMapAns() {
        return stuList.stream().map(StatusStudent::getName).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
    }

    public String getLinkedMethodAns() {
        List<StatusStudent> list = new ArrayList<>(stuList);
        list.add(new StatusStudent(4, null, StatusStudent.Status.BUSY));
        return list.stream()
                .filter(stu -> stu.getAge() > 3)
                .filter(stu -> stu.getStatus().equals(StatusStudent.Status.BUSY))
                .map(StatusStudent::getName)
                .filter(name -> name.startsWith("f"))
                .map(String::toUpperCase)
                .findFirst()
                .orElse("NOT HAVE THIS DATA");
    }
}
