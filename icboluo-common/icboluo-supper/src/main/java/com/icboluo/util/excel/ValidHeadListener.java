package com.icboluo.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.util.ClassUtils;
import com.icboluo.annotation.Excel;
import com.icboluo.interceptor.WebContext;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.I18nException;
import com.icboluo.util.SpringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 表头校验
 *
 * @author icboluo
 * @since 2023-08-03 21:11
 */
public class ValidHeadListener<T> extends ExcelListener<T> {

    private static final Map<Class<?>, TreeMap<Integer, Field>> CLASS_NAME_FIELD_CACHE = new ConcurrentHashMap<>();

    @Getter
    protected static final MessageSource MESSAGE_SOURCE = SpringUtil.getBean(MessageSource.class);

    /**
     * <p>是否在模版校验过程中，提示sheet名称
     * <p>如果是单个sheet，没必要增加sheet名称展示功能
     * <p>如果是多个sheet，需要不同的sheet页做区分
     */
    @Setter
    private boolean isShowSheetName;

    /**
     * 需要校验的行数，默认和head一致
     */
    @Setter
    protected int validateHead;

    /**
     * 模版校验是否需要抛异常
     */
    @Setter
    protected boolean isTemplateValidThrow = true;

    @Getter
    protected String headErrorMsg;

    /**
     * 无参构造，cglib代理的时候使用，正常情况下不需要使用
     */
    public ValidHeadListener() {
        this.clazz = BeanUtil.cast(Object.class);
    }

    public ValidHeadListener(Class<T> excelType) {
        this.clazz = excelType;
        this.validateHead = this.head;
        toCache();
    }

    public ValidHeadListener(Class<T> excelType, int headNum) {
        this.clazz = excelType;
        this.head = headNum;
        this.validateHead = this.head;
        toCache();
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (!StringUtils.hasText(sheetName)) {
            sheetName = context.readSheetHolder().getSheetName();
        }
        if (context.readSheetHolder().getRowIndex() != validateHead - 1) {
            return;
        }
        StringBuilder msg = new StringBuilder();
        for (Map.Entry<Integer, Field> entry : CLASS_NAME_FIELD_CACHE.get(clazz).entrySet()) {
            Integer entityIndex = entry.getKey();
            Field entityField = entry.getValue();
            Excel excel = entityField.getAnnotation(Excel.class);
            String excelCell = headMap.get(entityIndex);
            boolean containI18n = excelCell.contains(excel.en()) || excelCell.contains(excel.zh());
            if (excelCell == null || !containI18n) {
                String tempMsg = MESSAGE_SOURCE.getMessage("line.{0}.error.should.contain.{1}",
                        new Object[]{ExcelUtil.convertToTitle(entityIndex + 1),
                                WebContext.isEn() ? excel.en() : excel.zh()}, LocaleContextHolder.getLocale());
                msg.append(tempMsg).append("<br/>");
            }
        }
        if (!StringUtils.hasText(msg.toString())) {
            return;
        }
        String templateMismatch = MESSAGE_SOURCE.getMessage("excel.template.mismatch", null, LocaleContextHolder.getLocale());
        if (isShowSheetName) {
            headErrorMsg = "[" + sheetName + "]" + templateMismatch + ":<br/>" + msg;
        } else {
            headErrorMsg = templateMismatch + ":<br/>" + msg;
        }
        if (isTemplateValidThrow) {
            throw new I18nException(headErrorMsg);
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
        Map<Class<?>, Object> fieldCacheMap = BeanUtil.cast(ClassUtils.FIELD_CACHE);
        fieldCacheMap.put(clazz, fieldCache);
    }

    public int getMaxLine() {
        return CLASS_NAME_FIELD_CACHE.get(clazz).lastKey();
    }
}
