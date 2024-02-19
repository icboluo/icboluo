package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.util.ClassUtils;
import com.icboluo.annotation.Excel;
import com.icboluo.util.ExcelExportResolve;
import com.icboluo.util.ExcelUtil;
import com.icboluo.util.IcBoLuoI18nException;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.data.util.CastUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
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
        toCache();
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        String msg = "";
        for (Map.Entry<Integer, Field> entry : CLASS_NAME_FIELD_CACHE.get(clazz).entrySet()) {
            Integer entityIndex = entry.getKey();
            Field entityField = entry.getValue();
            Excel excel = entityField.getAnnotation(Excel.class);
            String excelCell = headMap.get(entityIndex);
            if (excelCell == null || !excelCell.contains(excel.zh())) {
                msg += "line: " + ExcelUtil.convertToTitle(entityIndex + 1) + " error," + "should be contain " + excel.zh() + " please check";
            }
        }
        if (StringUtils.hasText(msg)) {
            throw new IcBoLuoI18nException(msg);
        }
    }

    @SneakyThrows
    private void toCache() {
        if (CLASS_NAME_FIELD_CACHE.containsKey(clazz)) {
            return;
        }
        TreeMap<Integer, Field> map = new TreeMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Excel.class)) {
                continue;
            }
            Excel excel = field.getAnnotation(Excel.class);
            ExcelExportResolve.shoichiIndex(field);
            map.put(excel.columnIndex(), field);
        }
        CLASS_NAME_FIELD_CACHE.put(clazz, map);
        // 这里强制指定Excel的导出顺序，要求监听器创建于EasyExcel之前
        Class<?> fieldCacheCla = Class.forName("com.alibaba.excel.util.ClassUtils$FIELD_CACHE");
        Constructor<?> constructor = fieldCacheCla.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Object fieldCache = constructor.newInstance(map, new TreeMap<>(), new HashMap<>());
        Map<Class<?>, Object> fieldCacheMap = CastUtils.cast(ClassUtils.FIELD_CACHE);
        fieldCacheMap.put(clazz, fieldCache);
    }
}
