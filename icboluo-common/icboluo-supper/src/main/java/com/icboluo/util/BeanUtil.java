package com.icboluo.util;


import com.alibaba.fastjson2.filter.ValueFilter;
import com.alibaba.fastjson2.util.TypeUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.icboluo.common.PageQuery;
import com.icboluo.enumerate.ReEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @author icboluo
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("unused")
public class BeanUtil {

    private static final String CONVERT_ERR_MESSAGE = "【数据转换】数据转换出错，目标对象{}构造函数异常";

    /**
     * 属性复制，也可以直接使用Spring的工具类
     * 全属性copy覆盖，无论是否为null
     * TODO 增加测试用例
     *
     * @param source 源对象
     * @param target 目标对象
     */
    @Deprecated
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 将一个对象中的属性copy到另一个对象中，需要默认无参的构造方法
     *
     * @param source 源对象
     * @param target 目标类
     * @param <T>    类的类型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, @NonNull Class<T> target) {
        T t;
        try {
            t = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, target.getName(), e);
            throw new I18nException(ReEnum.DATA_TRANSFER_ERROR);
        }
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static <T> T copyProperties(Object source, @NonNull Supplier<T> supplier) {
        T t = supplier.get();
        BeanUtils.copyProperties(source, t);
        return t;
    }

    /**
     * 将list集合中的属性copy到目标对象的集合中
     *
     * @param sourceList 原集合
     * @param target     目标类
     * @param <T>        目标类的类型
     * @return 目标集合
     */
    public static <T> List<T> copyWithColl(List<?> sourceList, @NonNull Class<T> target) {
        return sourceList.stream()
                .map(s -> copyProperties(s, target))
                .toList();
    }

    public static <T> List<T> copyWithColl(List<?> sourceList, @NonNull Supplier<T> supplier) {
        return sourceList.stream()
                .map(s -> copyProperties(s, supplier))
                .toList();
    }

    public static <S, T> List<T> copyWithColl(List<S> sourceList, @NonNull Supplier<T> supplier, BiConsumer<S, T> callBack) {
        return sourceList.stream()
                .map(s -> {
                    T t = copyProperties(s, supplier);
                    if (callBack != null) {
                        callBack.accept(s, t);
                    }
                    return t;
                }).toList();
    }

    /**
     * 将set集合中的属性copy到目标对象的集合中
     *
     * @param sourceList 原集合
     * @param target     目标类
     * @param <T>        目标类的类型
     * @return 目标集合
     */
    public static <T> Set<T> copyWithColl(Set<?> sourceList, @NonNull Class<T> target) {
        return sourceList.stream()
                .map(s -> copyProperties(s, target))
                .collect(Collectors.toSet());
    }

    /**
     * 获取list的第一个元素
     *
     * @param coll list 容器
     * @param <T>  容器元素类型
     * @return 如果容器是空的，或者第一个元素是空的，返回 null
     */
    public static <T> T listGetFirst(Collection<T> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return null;
        }
        Optional<T> first = coll.stream()
                .findFirst();
        return first.orElse(null);
    }

    public static <T> T singletonGetFirst(Collection<T> coll) {
        if (CollectionUtils.isEmpty(coll) || coll.size() != 1) {
            return null;
        }
        Optional<T> first = coll.stream()
                .findFirst();
        return first.orElse(null);
    }

    @SafeVarargs
    public static <T> boolean allIsNull(T... arr) {
        return Arrays.stream(arr)
                .map(Objects::isNull)
                .reduce(true, (a, b) -> a && b);
    }

    @SafeVarargs
    public static <T> boolean allNotNull(T... arr) {
        return Arrays.stream(arr)
                .map(Objects::nonNull)
                .reduce(true, (a, b) -> a && b);
    }

    @SafeVarargs
    public static <T> boolean anyEmpty(T... arr) {
        return Arrays.stream(arr)
                .map(Objects::nonNull)
                .reduce(false, (a, b) -> a || b);
    }

    @SafeVarargs
    public static <T> boolean haveNull(T... arr) {
        return Arrays.stream(arr)
                .anyMatch(Objects::isNull);
    }

    public static <T> boolean allIsNullOrEquals(T a, T b) {
        if (a == null && b == null) {
            return true;
        }
        return a != null && a.equals(b);
    }

    /**
     * 将字节转换为bool
     *
     * @param by 字节 1|0
     * @return bool值
     * @deprecated 请使用fastjson工具类的api
     */
    @Deprecated
    public static Boolean byteToBoolean(Byte by) {
        return TypeUtils.cast(by, Boolean.class);
    }

    /**
     * 将bool值转换为字节
     *
     * @param bool bool值
     * @return 字节0|1
     * @deprecated 请使用fastjson工具类的api
     */
    @Deprecated
    public static Byte booleanToByte(Boolean bool) {
        return TypeUtils.cast(bool, Byte.class);
    }

    /**
     * TypeUtils 拥有数据类型转换函数
     *
     * @param val 对象值
     * @return int值
     * @deprecated 不应该调用此方法，直接调用type转换工具类即可
     */
    @Deprecated(since = "all")
    public static Integer toInt(Object val) {
        return TypeUtils.cast(val, Integer.TYPE);
    }

    @Deprecated
    public static <T> String join(Collection<T> coll) {
        return StringUtils.collectionToDelimitedString(coll, ";");
    }

    public static <K1, K2, V> Map<K2, V> mapKeyConvert(Map<K1, V> map, Function<K1, K2> keyConvert) {
        return mapConvert(map, keyConvert, item -> item);
    }

    public static <K, V1, V2> Map<K, V2> mapValConvert(Map<K, V1> map, Function<V1, V2> valConvert) {
        return mapConvert(map, item -> item, valConvert);
    }

    public static <K1, K2, V1, V2> Map<K2, V2> mapConvert(@NonNull Map<K1, V1> map, Function<K1, K2> keyConvert, Function<V1, V2> valConvert) {
        return map.entrySet().stream()
                .filter(entry -> allNotNull(entry.getKey(), keyConvert.apply(entry.getKey())))
                .filter(entry -> allNotNull(entry.getKey(), valConvert.apply(entry.getValue())))
                .collect(Collectors.toMap(
                        entry -> keyConvert.apply(entry.getKey()),
                        entry -> valConvert.apply(entry.getValue())));
    }

    /**
     * map反转 （val的唯一性自行判断
     *
     * @param map 原本的键值对
     * @param <K> key 类型
     * @param <V> val 类型
     * @return 反转后的键值对
     */
    public static <K, V> Map<V, K> reverseMap(Map<K, V> map) {
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static <S, T> PageInfo<T> pageInfoConvert(PageInfo<S> source, List<T> target) {
        Page<T> page = new Page<>(source.getPageNum(), source.getPageSize());
        page.setTotal(source.getTotal());
        PageInfo<T> pageInfo = PageInfo.of(page);
        pageInfo.setList(target);
        return pageInfo;
    }

    public static <S, T> PageInfo<T> pageInfoConvert(List<S> source, List<T> target) {
        return pageInfoConvert(PageInfo.of(source), target);
    }

    /**
     * 假分页
     *
     * @param list    原集合
     * @param convert 转换函数
     * @param query   分页条件
     * @param <S>     source
     * @param <T>     target
     * @param <Q>     query
     * @return page info
     */
    public static <S, T, Q extends PageQuery> PageInfo<T> fakePage(List<S> list, Function<List<S>, List<T>> convert, Q query) {
        int start = (query.getPageNum() - 1) * query.getPageSize();
        int end = Math.min(list.size(), query.getPageSize() * query.getPageNum());
        List<S> subList;
        if (start > list.size()) {
            int mo = list.size() % query.getPageSize();
            subList = list.subList(list.size() - mo, list.size());
        } else {
            subList = list.subList(start, Math.min(end, list.size()));
        }
        List<T> apply = convert.apply(subList);
        PageInfo<T> pi = PageInfo.of(apply);
        pi.setTotal(list.size());
        pi.setPageNum(query.getPageNum());
        pi.setPageSize(query.getPageSize());
        pi.setPages((list.size() + query.getPageSize() - 1) / query.getPageSize());
        return pi;
    }

    public static <S, Q extends PageQuery> PageInfo<S> fakePage(List<S> list, Q query) {
        return fakePage(list, a -> a, query);
    }

    public static <S> PageInfo<S> fakePage(Collection<S> coll) {
        if (coll instanceof List<S> list) {
            return PageInfo.of(list);
        }
        return PageInfo.of(new ArrayList<>(coll));
    }

    public static ValueFilter localDateTimeValueFilter() {
        return (obj, name, val) -> {
            if (val == null) {
                val = "";
            }
            if (val instanceof LocalDate localDateVal) {
                val = localDateVal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            if (val instanceof LocalDateTime localDateTimeVal) {
                val = localDateTimeVal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
            return val;
        };
    }

    public static <T> void batchConsumer(List<T> list, Consumer<List<T>> consumer) {
        batchConsumer(list, consumer, 5000);
    }

    public static <T> void batchConsumer(List<T> list, Consumer<List<T>> con, int size) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i += size) {
            List<T> subList = list.subList(i, Math.min(i + size, list.size()));
            con.accept(subList);
        }
    }

    public static <T> void batchConsumerSkipFail(List<T> list, Consumer<List<T>> consumer, Consumer<T> log) {
        try {
            batchConsumer(list, consumer);
        } catch (Exception ex) {
            if (list.size() == 1) {
                log.accept(list.get(0));
                return;
            }
            batchConsumerSkipFail(list.subList(0, list.size() / 2), consumer, log);
            batchConsumerSkipFail(list.subList(list.size() / 2, list.size()), consumer, log);
        }
    }

    /**
     * 将列表最大分成max组（自行判断传参的合理性：eg：分组大小不能为0，list不能为null...）
     *
     * @param stream       原始列表流
     * @param maxGroupSize 最大分组大小
     * @param <T>          元素类型
     * @return 分组后列表
     */
    public static <T> List<List<T>> groupBySize(Stream<T> stream, int maxGroupSize) {
        List<T> list = stream.toList();
        if (maxGroupSize > list.size()) {
            return list.stream().map(Arrays::asList).toList();
        }
        List<List<T>> res = new ArrayList<>();
        for (int i = 0; i < maxGroupSize; i++) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i < list.size(); i++) {
            res.get(i % res.size()).add(list.get(i));
        }
        return res;
    }

    /**
     * 根据一组大小分组，限制每组的最大数量
     *
     * @param coll 集合
     * @param size 每组最大大小
     * @param <T>  元素类型
     * @return 分组后的集合
     * @see org.apache.commons.collections4.ListUtils#partition(List, int) 使用这个替换即可
     */
    @Deprecated
    public static <T> List<List<T>> groupByOneGroupSize(Collection<T> coll, int size) {
        List<T> list = new ArrayList<>(coll);
        List<List<T>> res = new ArrayList<>();
        for (int i = 0; i < (coll.size() + size - 1) / size; i++) {
            List<T> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (i * size + j < list.size()) {
                    row.add(list.get(i * size + j));
                }
            }
            res.add(row);
        }
        return res;
    }

    private static final Map<Class<?>, Map<String, Field>> CLASS_NAME_FIELD = new HashMap<>();

    /**
     * 将源对象的属性值合并到目标对象中
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void mergeProperties(Object source, Object target) {
        source = Optional.ofNullable(source).map(ObjectUtils::unwrapOptional).orElse(null);
        target = Optional.ofNullable(target).map(ObjectUtils::unwrapOptional).orElse(null);
        if (source == null || target == null) {
            return;
        }
        cacheCLassNameFieldMap(source.getClass());
        cacheCLassNameFieldMap(target.getClass());
        mergePropertiesNotValid(source, target);
    }

    @SneakyThrows
    private static void mergePropertiesNotValid(Object source, Object target) {
        for (Map.Entry<String, Field> entry : CLASS_NAME_FIELD.get(target.getClass()).entrySet()) {
            String name = entry.getKey();
            Field targetField = entry.getValue();
            Map<String, Field> sourceMap = CLASS_NAME_FIELD.get(source.getClass());
            Field sourceField = sourceMap.get(name);
            targetField.setAccessible(true);
            if (targetField.get(target) == null && sourceMap.containsKey(name) && targetField.getType() == sourceField.getType()) {
                sourceField.setAccessible(true);
                targetField.set(target, sourceField.get(source));
            }
        }
    }

    private static void cacheCLassNameFieldMap(Class<?> clazz) {
        if (CLASS_NAME_FIELD.containsKey(clazz)) {
            return;
        }
        List<Field> fields = getThisAndSupperDeclaredFields(clazz);
        Map<String, Field> nameFieldMap = fields.stream()
                .collect(Collectors.toMap(Field::getName, Function.identity(), (fir, sec) -> sec));
        CLASS_NAME_FIELD.put(clazz, nameFieldMap);
    }

    /**
     * <p/>获取本类和父类的所有字段
     * <p/> for(Field field : BeanUtil.getThisAndSupperDeclaredFields(client.getClass())) {} 增强for循环写成这样, 后面的方法是不会循环执行的
     *
     * @param clazz 当前类
     * @return 所有字段
     */
    public static List<Field> getThisAndSupperDeclaredFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        Set<String> nameSet = new HashSet<>();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) || nameSet.add(field.getName())) {
                    continue;
                }
                list.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }

    /**
     * <p>修改类字段的注解值，给注解赋值
     * <p>警告：危险函数，该方法是给Field的注解赋值，修改之后，该字段的注解值会保留下来，该类的注解值会被彻底改变
     *
     * @param field     待修改字段
     * @param annoClass 注解类型
     * @param paramName 注解名
     * @param paramVal  需要修改的注解值
     * @param <A>       注解类型
     */
    @SneakyThrows
    public static <A extends Annotation> void updateAnnoVal(Field field, Class<A> annoClass, String paramName, Object paramVal) {
        A anno = field.getAnnotation(annoClass);
        // 获取 anno 这个代理实例所持有的 InvocationHandler
        InvocationHandler ih = Proxy.getInvocationHandler(anno);
        // 获取 AnnotationInvocationHandler 的 memberValues 字段
        Field ihField = ih.getClass().getDeclaredField("memberValues");
        // 因为这个字段是 private final 修饰，所以要打开权限
        ihField.setAccessible(true);
        // 获取 memberValues
        Map<String, Object> memberValues = (Map<String, Object>) ihField.get(ih);
        memberValues.put(paramName, paramVal);
    }

    /**
     * 去除首尾双引号
     *
     * @param str 源字符串
     * @return 当首尾有双引号的时候，清理多余的首尾双引号
     */
    public static String cleanFirstLastDoubleQuotes(String str) {
        if (!StringUtils.hasText(str) || str.charAt(0) != '"' || str.charAt(str.length() - 1) != '"') {
            return str;
        }
        return str.substring(0, str.length() - 1).replaceFirst("\"", "");
    }

    /**
     * 判断不区分大小写，大的集合是否包含小的集合
     *
     * @param bigList   大的集合
     * @param smallList 小的集合
     * @return 如果大的集合包含小的集合，返回true
     */
    public static boolean containIgnoreCare(List<String> bigList, List<String> smallList) {
        for (String small : smallList) {
            boolean flag = false;
            for (String big : bigList) {
                if (big.equalsIgnoreCase(small)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从列表中删除指定集合中包含的所有元素
     *
     * @param bigList   较大的集合
     * @param smallList 需要删除元素的集合
     */
    public static void removeAllIgnoreCase(List<String> bigList, List<String> smallList) {
        for (int i = bigList.size() - 1; i >= 0; i--) {
            String big = bigList.get(i);
            for (String small : smallList) {
                if (big.equalsIgnoreCase(small)) {
                    bigList.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * + 拼接字符串切割为数组
     *
     * @param str     待切割字符串
     * @param convert 字符串转换函数
     * @param <V>     转换后的值类型
     * @return 字符粗数组
     */
    public static <V> List<V> parseJoin(String str, Function<String, V> convert) {
        if (StringUtils.hasText(str)) {
            return Arrays.stream(str.split(";")).filter(StringUtils::hasText).map(convert).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

    public static boolean containIgnoreCase(List<String> bigList, String val) {
        for (String big : bigList) {
            if (big.equalsIgnoreCase(val)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 移除字符串的后缀
     *
     * @param str    源字符串
     * @param suffix 后缀
     * @return 移除后的字符串
     */
    public static String removeSuffix(String str, String suffix) {
        if (!StringUtils.hasText(str)) {
            return str;
        }
        if (str.endsWith(suffix)) {
            return str.substring(0, str.lastIndexOf(suffix));
        }
        return str;
    }

    public static <T> void sortByList(List<T> list, List<T> sortArr) {
        sortByList(list, sortArr, Function.identity(), Function.identity());
    }

    public static <T, S, C> void sortByList(List<T> list, List<S> sortArr, Function<T, C> gen1, Function<S, C> gen2) {
        Map<C, Integer> idxEleMap = IntStream.range(0, sortArr.size())
                .boxed()
                .collect(Collectors.toMap(i -> gen2.apply(sortArr.get(i)), Function.identity(), (a, v2) -> v2));
        Comparator<T> comparator = (a, b) -> {
            Integer aOrder = idxEleMap.get(gen1.apply(a));
            Integer bOrder = idxEleMap.get(gen1.apply(b));
            if (aOrder == null && bOrder == null) {
                return 0;
            }
            // 处理不在sortArr中的元素，将其排在最后
            if (aOrder == null) {
                return 1;
            }
            if (bOrder == null) {
                return -1;
            }
            return aOrder.compareTo(bOrder);
        };
        list.sort(comparator);
    }

    public static List<String> compareObjectReturnDifferentField(Object obj1, Object obj2, String... excludeFields) {
        List<String> excludeList = Arrays.stream(excludeFields).filter(Objects::nonNull).collect(Collectors.toList());
        return compareObjectReturnDifferentField(obj1, obj2, null, excludeList);
    }

    @SneakyThrows
    public static List<String> compareObjectReturnDifferentField(Object obj1, Object obj2, List<String> includeList, List<String> excludeList) {
        Predicate<Field> include = fie -> CollectionUtils.isEmpty(includeList) || includeList.contains(fie.getName());
        Predicate<Field> exclude = fie -> !CollectionUtils.isEmpty(excludeList) && excludeList.contains(fie.getName());
        Field[] fields1 = BeanUtil.getThisAndSupperDeclaredFields(obj1.getClass()).toArray(new Field[0]);
        Field[] fields2 = BeanUtil.getThisAndSupperDeclaredFields(obj2.getClass()).toArray(new Field[0]);
        Map<String, Field> field2Map = Arrays.stream(fields2).collect(Collectors.toMap(Field::getName, Function.identity()));
        List<String> res = new ArrayList<>();
        for (Field field1 : fields1) {
            // 跳过条件：静态字段, 没在包含字段里面， 在排除字段里面
            if (Modifier.isStatic(field1.getModifiers()) || !include.test(field1) || exclude.test(field1)) {
                continue;
            }
            // BeanUtil.setAccessible(field1);
            if (!field2Map.containsKey(field1.getName())) {
                res.add(field1.getName());
                continue;
            }
            Field field2 = field2Map.get(field1.getName());
            // BeanUtil.setAccessible(field2);
            Object value1 = field1.get(obj1);
            Object value2 = field2.get(obj2);
            boolean fieldIsSame = fieldIsSame(field1, value1, field2, value2);
            if (!fieldIsSame) {
                res.add(field1.getName());
            }
        }
        return res;
    }

    private static boolean fieldIsSame(Field field1, Object val1, Field field2, Object val2) {
        if (field1.getType() == BigDecimal.class && field2.getType() == BigDecimal.class) {
            return MathUtil.isSame((BigDecimal) val1, (BigDecimal) val2);
        } else if (field1.getType() == String.class && field2.getType() == String.class) {
            String str1 = (String) val1;
            String str2 = (String) val2;
            if (StringUtils.hasText(str1) && StringUtils.hasText(str2)) {
                return str1.trim().equals(str2.trim());
            }
            return !StringUtils.hasText(str1) && !StringUtils.hasText(str2);
        } else {
            return Objects.equals(val1, val2);
        }
    }
}
