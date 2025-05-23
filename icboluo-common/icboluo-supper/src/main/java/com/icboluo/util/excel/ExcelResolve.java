package com.icboluo.util.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.icboluo.annotation.Excel;
import com.icboluo.annotation.I18n;
import com.icboluo.interceptor.WebContext;
import com.icboluo.util.BeanUtil;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <p> 声明：Excel表头字段的获取方式：
 * <p> 1.固定字段---对于固定字段，可以直接在视图上增加注解，使用 EasyExcel 原生 API 即可
 * <p> 2.外部输入---（前段传参、数据库提前配置好），视图的字段名需要和参数有一定的关系 this.paramName
 * <p> Excel 导出解析器
 * <p> 使用方式如下
 * <p> <pre>{@code ExcelUtil.write(aList, A.class, fileName);}<pre/>
 *
 * @author icboluo
 * @see Excel excel一切功能来源均取自该注解
 * @since 2023-06-25 18:50
 */
@Slf4j
public class ExcelResolve<T> {

    /**
     * 导出模版类型
     */
    private final Class<T> clazz;

    /**
     * 排序号的字段名，如果不 set ，认为无自定义排序，按照注解排序
     */
    @Setter
    private List<String> sortFieldName;

    public ExcelResolve(Class<T> clazz) {
        this.clazz = clazz;
        this.sortFieldName = defaultSortFieldName();
    }

    public static List<Field> excelField(Class<?> cla) {
        return BeanUtil.getThisAndSupperDeclaredFields(cla)
                .stream().filter(f -> f.isAnnotationPresent(Excel.class))
                .peek(ExcelResolve::shoichiIndex)
                .filter(f -> f.getAnnotation(Excel.class).columnIndex() > -1)
                .toList();
    }

    public static int maxIdx(Class<?> cla) {
        return excelField(cla)
                .stream()
                .map(field -> field.getAnnotation(Excel.class))
                .map(Excel::columnIndex)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public static SortedMap<Integer, Field> getIndexField(Class<?> cla) {
        return excelField(cla)
                .stream()
                .collect(Collectors.toMap(field -> field.getAnnotation(Excel.class).columnIndex(), Function.identity(),
                        (k1, k2) -> k1, TreeMap::new));
    }

    private List<String> defaultSortFieldName() {
        int max = ExcelResolve.maxIdx(clazz);
        String[] arr = new String[max + 1];
        for (Field field : excelField(clazz)) {
            Excel excel = field.getAnnotation(Excel.class);
            arr[excel.columnIndex()] = field.getName();

        }
        return Arrays.stream(arr).collect(Collectors.toList());
    }

    /**
     * 清理字段
     *
     * @param fields 待清理字段
     */
    public void cleanField(String... fields) {
        List<String> fieldList = Arrays.stream(fields).toList();
        for (int i = sortFieldName.size() - 1; i >= 0; i--) {
            if (fieldList.contains(sortFieldName.get(i))) {
                sortFieldName.remove(i);
            }
        }
    }


    /**
     * 归一（该方法永久的修改了字段的注解值，仅需要调用一次
     *
     * @param field 字段
     */
    public static void shoichiIndex(Field field) {
        Excel excel = field.getAnnotation(Excel.class);
        Map<String, Object> memberValues = getMemberValues(excel);
        if (StringUtils.hasText(excel.columnNumber())) {
            memberValues.put("columnIndex", ExcelUtil.titleToNumber(excel.columnNumber()) - 1);
        } else if (field.isAnnotationPresent(ExcelProperty.class)) {
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            memberValues.put("columnIndex", property.index());
        }
        if (excel.columnIndex() < 0) {
            log.error("Class [{}], Field name [{}] columnIndex is -1, However, a drop-down list box needs to be generated, it`s anomalous, Please check",
                    field.getDeclaringClass().getSimpleName(), field.getName());
        }
        if (field.isAnnotationPresent(ExcelProperty.class)) {
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            Map<String, Object> propertyMember = getMemberValues(property);
            propertyMember.put("index", excel.columnIndex());
        }
    }

    private String searchI18nValue(Field field) {
        // AnnotatedElementUtils 完全支持 AliasFor 注解
        // AnnotationUtils 支持的是元注解
        I18n i18n = AnnotatedElementUtils.findMergedAnnotation(field, I18n.class);
        if (i18n == null) {
            return field.getName();
        }
        if (StringUtils.hasText(i18n.en()) && StringUtils.hasText(i18n.zh())) {
            return WebContext.isEn() ? i18n.en() : i18n.zh();
        }
        // 如果只填一个中文或者英文，尽可能的适配表头
        if (StringUtils.hasText(i18n.en())) {
            return i18n.en();
        }
        if (StringUtils.hasText(i18n.zh())) {
            return i18n.zh();
        }
        // 如果中英文都没有配置，则取ExcelProperty 上的属性
        if (field.isAnnotationPresent(ExcelProperty.class)) {
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty.value().length != 0) {
                return excelProperty.value()[0];
            }
        }
        return field.getName();
    }


    @SneakyThrows
    public List<List<String>> head() {
        List<List<String>> res = new ArrayList<>();
        for (String sortField : sortFieldName) {
            List<String> cell = new ArrayList<>();
            if (sortField == null) {
                cell.add("");
            } else {
                Field field = clazz.getDeclaredField(sortField);
                cell.add(this.searchI18nValue(field));
            }
            res.add(cell);
        }
        return res;
    }

    @SneakyThrows
    public <V> List<List<Object>> body(Supplier<List<V>> selectList) throws IllegalAccessException {
        List<V> list = selectList.get();
        List<List<Object>> res = new ArrayList<>();
        for (V v : list) {
            List<Object> row = new ArrayList<>();
            for (String sortField : sortFieldName) {
                if (sortField == null) {
                    row.add("");
                } else {
                    Field field = clazz.getDeclaredField(sortField);
                    field.setAccessible(true);
                    row.add(field.get(v));
                }
            }
            res.add(row);
        }
        return res;
    }

    /**
     * 获取注解的属性map
     *
     * @param anno 注解
     * @return 属性map
     */
    @SneakyThrows
    public static Map<String, Object> getMemberValues(Object anno) {
        InvocationHandler ih = Proxy.getInvocationHandler(anno);
        Field mv = ih.getClass().getDeclaredField("memberValues");
        mv.setAccessible(true);
        // --add-opens java.base/sun.reflect.annotation=ALL-UNNAMED  启动失败加 jvm参数即可解决
        return (Map<String, Object>) mv.get(ih);
    }
}
