package com.icboluo.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.icboluo.annotation.Excel;
import com.icboluo.util.BeanUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * Excel按照列名读取数据的监听器
 *
 * @author icboluo
 * @since 2026-02-04 21:24
 */
public class ColumnNameReadListener<T> extends AnalysisEventListener<LinkedHashMap<Integer, String>> {
    @Getter
    private final List<T> list = new ArrayList<>();
    private final Class<T> clazz;
    /**
     * key：索引序号，val：
     */
    private final Map<Integer, BiConsumer<T, String>> setMap = new HashMap<>();

    public ColumnNameReadListener(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        List<Field> fields = ExcelResolve.excelField(clazz);
        for (Map.Entry<Integer, String> entry : headMap.entrySet()) {
            // excel对应的列号
            Integer columnNo = entry.getKey();
            // excel对应的head名
            String cell = entry.getValue();
            if (cell == null) {
                continue;
            }
            for (Field field : fields) {
                Excel excel = field.getAnnotation(Excel.class);
                if (excel == null) {
                    continue;
                }
                // 如果Excel的中文或者英文，包含实体类字段注解上的中英文
                if (StringUtils.hasText(excel.en()) && BeanUtil.containIgnoreCase(cell, excel.en())) {
                    // 使用反射给字段设置值
                    setMap.put(columnNo, (db, val) -> BeanUtil.fieldSetValue(field, db, val));
                    break;
                }
                if (StringUtils.hasText(excel.zh()) && BeanUtil.containIgnoreCase(cell, excel.zh())) {
                    // 使用反射给字段设置值
                    setMap.put(columnNo, (db, val) -> BeanUtil.fieldSetValue(field, db, val));
                    break;
                }
            }
        }
    }

    @Override
    @SneakyThrows(value = {NoSuchMethodException.class, ReflectiveOperationException.class})
    public void invoke(LinkedHashMap<Integer, String> data, AnalysisContext context) {
        T row = clazz.getConstructor().newInstance();
        for (Map.Entry<Integer, BiConsumer<T, String>> entry : setMap.entrySet()) {
            String cell = data.get(entry.getKey());
            BiConsumer<T, String> biCos = entry.getValue();
            biCos.accept(row, cell);
        }
        list.add(row);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }
}

