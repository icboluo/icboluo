package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.icboluo.annotation.ExcelExport;
import com.icboluo.util.ExcelExportResolve;
import com.icboluo.util.IcBoLuoI18nException;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-08-03 21:11
 */
public class ValidHeadExcelListener<T> extends ExcelListener<T> {

    private static final Map<Class<?>, Map<Integer, Field>> CLASS_NAME_FIELD_CACHE = new HashMap<>();
    /**
     * 解析类型
     */
    protected final Class<T> clazz;

    @Getter
    protected final int head;

    public ValidHeadExcelListener(Class<T> excelType, int headNum) {
        this.clazz = excelType;
        this.head = headNum;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        for (Map.Entry<Integer, Field> entry : toCache().entrySet()) {
            Integer entityIndex = entry.getKey();
            Field entityField = entry.getValue();
            ExcelExport excelExport = entityField.getAnnotation(ExcelExport.class);
            String excelCell = headMap.get(entityIndex);
            if (excelCell == null || !excelCell.contains(excelExport.columnNumber())) {
                throw new IcBoLuoI18nException("index: " + entityIndex + " error, please check");
            }
        }
    }

    private Map<Integer, Field> toCache() {
        if (CLASS_NAME_FIELD_CACHE.containsKey(clazz)) {
            return CLASS_NAME_FIELD_CACHE.get(clazz);
        }
        Map<Integer, Field> map = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(ExcelExport.class)) {
                continue;
            }
            ExcelExport excel = field.getAnnotation(ExcelExport.class);
            ExcelExportResolve.shoichiIndex(field);
            map.put(excel.columnIndex(), field);
        }
        CLASS_NAME_FIELD_CACHE.put(clazz, map);
        return map;
    }
}
