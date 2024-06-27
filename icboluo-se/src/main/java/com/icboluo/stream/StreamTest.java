package com.icboluo.stream;

import com.icboluo.object.StatusStudent;
import org.junit.jupiter.api.Assertions;
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


    @Test
    public void getAllStuNameAns() {
        List<String> ans = stuList.stream().map(StatusStudent::getName).toList();

        Assertions.assertEquals(ans, getAllStuName());
    }

    @Test
    public void busyStuCountAns() {
        Integer ans = (int) stuList.stream().filter(stu -> StatusStudent.Status.BUSY.equals(stu.getStatus())).count();

        Assertions.assertEquals(ans, busyStuCount());
    }

    @Test
    public void countStatusAns() {
        Integer ans = (int) stuList.stream().map(StatusStudent::getStatus).distinct().count();

        Assertions.assertEquals(ans, countStatus());
    }

    @Test
    public void limit3Ans() {
        List<StatusStudent> ans = stuList.stream().limit(3).toList();

        Assertions.assertEquals(ans, limit3());
    }

    @Test
    public void toUpperCaseAns() {
        List<String> ans = stuList.stream().map(StatusStudent::getName).map(String::toUpperCase).toList();

        Assertions.assertEquals(ans, toUpperCase());
    }

    @Test
    public void sortAns() {
        List<StatusStudent> ans = stuList.stream().sorted((a, b) -> b.getAge() - a.getAge()).toList();

        Assertions.assertEquals(ans, sort());
    }

    @Test
    public void containCharAns() {
        List<Character> ans = stuList.stream()
                .map(StatusStudent::getName)
                .map(str -> IntStream.range(0, str.length()).mapToObj(str::charAt).toList())
                .flatMap(Collection::stream)
                .distinct()
                .toList();

        Assertions.assertEquals(ans, containChar());
    }

    @Test
    public void ageMaxAndMinAns() {
        int[] ans = new int[3];
        IntSummaryStatistics summary = stuList.stream().collect(Collectors.summarizingInt(StatusStudent::getAge));
        ans[0] = summary.getMin();
        ans[1] = summary.getMax();
        ans[2] = (int) summary.getCount();

        Assertions.assertEquals(ans, ageMaxAndMin());
    }

    @Test
    public void ageIsSmall3Ans() {
        Boolean ans = stuList.stream().anyMatch(stu -> stu.getAge() < 3);

        Assertions.assertEquals(ans, ageIsSmall3());
    }

    @Test
    public void findFirstStatusIsFreeAns() {
        Optional<StatusStudent> ans = stuList.stream().filter(stu -> stu.getStatus().equals(StatusStudent.Status.FREE)).findFirst();

        Assertions.assertEquals(ans, findFirstStatusIsFree());
    }

    @Test
    public void ageMultiplyAns() {
        Integer ans = stuList.stream().map(StatusStudent::getAge).reduce(1, (a, b) -> a * b);

        Assertions.assertEquals(ans, ageMultiply());
    }

    @Test
    public void groupByStatusAns() {
        Map<StatusStudent.Status, List<StatusStudent>> ans = stuList.stream().collect(Collectors.groupingBy(StatusStudent::getStatus));

        Assertions.assertEquals(ans, groupByStatus());
    }

    @Test
    public void groupByAgeAns() {
        Map<Boolean, List<StatusStudent>> ans = stuList.stream().collect(Collectors.partitioningBy(stu -> stu.getAge() > 3));

        Assertions.assertEquals(ans, groupByAge());
    }

    @Test
    public void joinNameAns() {
        String ans = stuList.stream().map(StatusStudent::getName).collect(Collectors.joining(";"));

        Assertions.assertEquals(ans, joinName());
    }

    @Test
    public void groupByStatusNameAns() {
        Map<StatusStudent.Status, Map<String, List<StatusStudent>>> ans = stuList.stream()
                .collect(Collectors.groupingBy(StatusStudent::getStatus, Collectors.groupingBy(StatusStudent::getName)));

        Assertions.assertEquals(ans, groupByStatusName());
    }

    @Test
    public void eleCountMapAns() {
        Map<String, Integer> ans = stuList.stream().map(StatusStudent::getName).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));

        Assertions.assertEquals(ans, eleCountMap());
    }

    @Test
    public void getLinkedMethodAns() {
        List<StatusStudent> list = new ArrayList<>(stuList);
        list.add(new StatusStudent(4, null, StatusStudent.Status.BUSY));
        String ans = list.stream()
                .filter(stu -> stu.getAge() > 3)
                .filter(stu -> stu.getStatus().equals(StatusStudent.Status.BUSY))
                .map(StatusStudent::getName)
                .filter(name -> name.startsWith("f"))
                .map(String::toUpperCase)
                .findFirst()
                .orElse("NOT HAVE THIS DATA");
        Assertions.assertEquals(ans, getLinkedMethod());
    }
}
