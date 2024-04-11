package com.icboluo.util.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.icboluo.annotation.Excel;
import com.icboluo.annotation.I18n;
import com.icboluo.interceptor.WebContext;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * <p> 声明：Excel表头字段的获取方式：
 * <p> 1.固定字段---对于固定字段，可以直接在视图上增加注解，使用 EasyExcel 原生 API 即可
 * <p> 2.外部输入---（前段传参、数据库提前配置好），视图的字段名需要和参数有一定的关系 this.paramName
 * <p> Excel 导出解析器
 * <p> 使用方式如下
 * <p> <pre>{@code ExcelUtil.exportList(aList, A.class, fileName);}<pre/>
 *
 * @author icboluo
 * @see Excel excel一切功能来源均取自该注解
 * @since 2023-06-25 18:50
 */
@Slf4j
public class ExcelExportResolve<T> {

    /**
     * 全局的容器需要使用线程安全的容器
     */
    private static final Map<Class<?>, Map<String, Field>> CLASS_NAME_FIELD_CACHE = new ConcurrentHashMap<>();

    /**
     * 导出模版类型
     */
    private final Class<T> clazz;

    /**
     * 名称，字段映射
     */
    private final Map<String, Field> nameFieldMap;

    /**
     * 排序号的字段名，如果不 set ，认为无自定义排序，按照注解排序
     */
    @Setter
    private List<String> sortFieldName;

    public ExcelExportResolve(Class<T> clazz) {
        this.clazz = clazz;
        this.toCache(clazz);
        this.nameFieldMap = CLASS_NAME_FIELD_CACHE.get(clazz);
        this.sortFieldName = defaultSortFieldName();
    }

    /**
     * 将类转换成缓存信息
     *
     * @param clazz 类
     */
    private void toCache(Class<T> clazz) {
        if (CLASS_NAME_FIELD_CACHE.containsKey(clazz)) {
            return;
        }
        Map<String, Field> classMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Excel.class)) {
                continue;
            }
            Excel excel = field.getAnnotation(Excel.class);
            if (StringUtils.hasText(excel.paramName())) {
                classMap.put(excel.paramName(), field);
            } else {
                classMap.put(field.getName(), field);
            }
        }
        CLASS_NAME_FIELD_CACHE.put(clazz, classMap);
    }

    private List<String> defaultSortFieldName() {
        Integer max = nameFieldMap.values().stream()
                .peek(ExcelExportResolve::shoichiIndex)
                .map(field -> field.getAnnotation(Excel.class))
                .map(Excel::columnIndex)
                .max(Integer::compareTo)
                .orElse(0);
        String[] arr = new String[max + 1];
        for (Map.Entry<String, Field> entry : nameFieldMap.entrySet()) {
            Excel excel = entry.getValue().getAnnotation(Excel.class);
            arr[excel.columnIndex()] = entry.getKey();
        }
        return Arrays.stream(arr).toList();
    }

    /**
     * 归一
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
        if (excel.columnIndex() == -1) {
            log.error("-11111");
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


    public List<List<String>> head() {
        List<List<String>> res = new ArrayList<>();
        for (String sortField : sortFieldName) {
            List<String> cell = new ArrayList<>();
            if (sortField == null) {
                cell.add("");
            } else {
                Field field = nameFieldMap.get(sortField);
                cell.add(this.searchI18nValue(field));
            }
            res.add(cell);
        }
        return res;
    }

    public <V> List<List<Object>> body(Supplier<List<V>> selectList) throws IllegalAccessException {
        List<V> list = selectList.get();
        List<List<Object>> res = new ArrayList<>();
        for (V v : list) {
            List<Object> row = new ArrayList<>();
            for (String sortField : sortFieldName) {
                if (sortField == null) {
                    row.add("");
                } else {
                    Field field = nameFieldMap.get(sortField);
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
