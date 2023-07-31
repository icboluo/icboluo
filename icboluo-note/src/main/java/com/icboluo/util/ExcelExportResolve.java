package com.icboluo.util;

import com.alibaba.excel.annotation.ExcelProperty;
import com.icboluo.annotation.ExcelExport;
import com.icboluo.annotation.I18n;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.function.Supplier;

/**
 * 声明：Excel表头字段的获取方式：
 * 1.固定字段---对于固定字段，可以直接在视图上增加注解，使用 EasyExcel 原生 API 即可
 * 2.外部输入---（前段传参、数据库提前配置好），视图的字段名需要和参数有一定的关系 this.paramName
 *
 * @author icboluo
 * @since 2023-06-25 18:50
 */
public class ExcelExportResolve<T> {

    private static final Map<Class<?>, Map<String, Field>> CLASS_NAME_FIELD_CACHE = new HashMap<>();

    /**
     * 导出模版类型
     */
    private Class<T> clazz;

    /**
     * 名称，字段映射
     */
    private Map<String, Field> nameFieldMap;

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
            if (!field.isAnnotationPresent(ExcelExport.class)) {
                continue;
            }
            ExcelExport excel = field.getAnnotation(ExcelExport.class);
            if (StringUtils.hasText(excel.paramName())) {
                classMap.put(excel.paramName(), field);
            } else {
                classMap.put(field.getName(), field);
            }
        }
        CLASS_NAME_FIELD_CACHE.put(clazz, classMap);
    }

    private List<String> defaultSortFieldName() {
        Integer max = nameFieldMap.values().stream().peek(field -> {
                    ExcelExport excel = field.getAnnotation(ExcelExport.class);
                    Map<String, Object> memberValues = getMemberValues(excel);
                    if (StringUtils.hasText(excel.columnNumber())) {
                        memberValues.put("columnIndex", titleToNumber(excel.columnNumber()) - 1);
                    } else {
                        if (field.isAnnotationPresent(ExcelProperty.class)) {
                            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
                            memberValues.put("columnIndex", property.index());
                        }
                    }
                }).map(field -> field.getAnnotation(ExcelExport.class))
                .map(ExcelExport::columnIndex)
                .max(Integer::compareTo)
                .orElse(0);
        String[] arr = new String[max + 1];
        for (Map.Entry<String, Field> entry : nameFieldMap.entrySet()) {
            ExcelExport excel = entry.getValue().getAnnotation(ExcelExport.class);
            arr[excel.columnIndex()] = entry.getKey();
        }
        return Arrays.stream(arr).toList();
    }


    public List<List<String>> head() {
        List<List<String>> res = new ArrayList<>();
        for (String sortField : sortFieldName) {
            List<String> cell = new ArrayList<>();
            if (sortField == null) {
                cell.add("");
            } else {
                Field field = nameFieldMap.get(sortField);
                I18n i18n = AnnotatedElementUtils.findMergedAnnotation(field, I18n.class);
                if (i18n == null) {
                    cell.add("");
                } else {
                    cell.add(i18n.zh() + System.currentTimeMillis());
                }
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

    /**
     * 0171 Excel 列号转数字，从1开始
     * 我们可以发现 BCM=(2*26+3)26+13
     *
     * @param columnTitle 列英文名称，不区分大小写
     * @return 列对应的序号，从1开始
     */
    public static int titleToNumber(String columnTitle) {
        if (columnTitle == null) {
            return -1;
        }
        int sum = 0;
        for (char ch : columnTitle.toUpperCase().toCharArray()) {
            sum *= 26;
            sum += ch - 'A' + 1;
        }
        return sum;
    }
}
