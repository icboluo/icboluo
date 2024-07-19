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
    /**
     * <p>是否在模版校验过程中，提示sheet名称
     * <p>如果是单个sheet，没必要增加sheet名称展示功能
     * <p>如果是多个sheet，需要不同的sheet页做区分
     */
    @Setter
    private boolean showSheetName;
    /**
     * 需要校验的行数，默认和head一致
     */
    @Setter
    protected int validateHead = excelEntity.headRowNumber;
    /**
     * 模版校验是否需要抛异常
     */
    @Setter
    protected boolean templateValidThrow = true;
    @Getter
    protected String headErrorMsg;

    public ValidHeadListener() {

    }

    public ValidHeadListener(Class<T> excelType) {
        super(excelType, new ExcelEntity<>());
    }

    public ValidHeadListener(Class<T> excelType, ExcelEntity<T> entity) {
        super(excelType, entity);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (context.readSheetHolder().getRowIndex() != validateHead - 1) {
            return;
        }
        StringBuilder msg = new StringBuilder();
        for (Map.Entry<Integer, Field> entry : ExcelExportResolve.CLASS_INDEX_FIELD_CACHE.get(excelEntity.clazz).entrySet()) {
            Integer entityIndex = entry.getKey();
            Field entityField = entry.getValue();
            Excel excel = entityField.getAnnotation(Excel.class);
            String excelCell = headMap.get(entityIndex);
            if (!headCellMatch(excelCell, excel)) {
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
        if (showSheetName) {
            headErrorMsg = "[" + excelEntity.sheetName + "]" + templateMismatch + ":<br/>" + msg;
        } else {
            headErrorMsg = templateMismatch + ":<br/>" + msg;
        }
        if (templateValidThrow) {
            throw new I18nException(headErrorMsg);
        }
    }

    private boolean headCellMatch(String excelCell, Excel excel) {
        if (!excel.check()) {
            return true;
        }
        // 如果字段标记了要进行表头校验，但是又没有配表头的中英文，则不校验；如果代码走到这个if分支，代表编码有问题
        if (!StringUtils.hasText(excel.en()) && !StringUtils.hasText(excel.zh())) {
            return true;
        }
        if (!StringUtils.hasText(excelCell)) {
            return false;
        }
        if (StringUtils.hasText(excel.en()) && excelCell.contains(excel.en())) {
            return true;
        }
        if (StringUtils.hasText(excel.zh()) && excelCell.contains(excel.zh())) {
            return true;
        }
        return false;
    }
}
