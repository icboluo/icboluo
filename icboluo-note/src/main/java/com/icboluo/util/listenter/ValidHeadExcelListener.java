package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.icboluo.annotation.ExcelExport;
import com.icboluo.util.ExcelExportResolve;
import com.icboluo.util.ExcelHelp;
import com.icboluo.util.IcBoLuoI18nException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
        String msg = "";
        for (Map.Entry<Integer, Field> entry : toCache().entrySet()) {
            Integer entityIndex = entry.getKey();
            Field entityField = entry.getValue();
            ExcelExport excelExport = entityField.getAnnotation(ExcelExport.class);
            String excelCell = headMap.get(entityIndex);
            if (excelCell == null || !excelCell.contains(excelExport.zh())) {
                msg += "line: " + ExcelHelp.convertToTitle(entityIndex + 1) + " error," + "should be contain " + excelExport.zh() + " please check";
            }
        }
        if (StringUtils.hasText(msg)) {
            throw new IcBoLuoI18nException(msg);
        }
    }

    private Map<Integer, Field> toCache() {
        if (CLASS_NAME_FIELD_CACHE.containsKey(clazz)) {
            return CLASS_NAME_FIELD_CACHE.get(clazz);
        }
        TreeMap<Integer, Field> map = new TreeMap<>();
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
