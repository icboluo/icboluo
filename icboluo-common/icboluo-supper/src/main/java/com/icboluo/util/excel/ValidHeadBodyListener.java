package com.icboluo.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.icboluo.annotation.Excel;
import com.icboluo.util.ValidateUtil;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 表头、表内容校验
 *
 * @author icboluo
 * @since 2023-08-03 21:56
 */
public class ValidHeadBodyListener<T> extends ExcelListener<T> {

    private ValidHeadListener<T> validHeadListener;
    private ValidBodyListener<T> validBodyListener;

    /**
     * 无参构造，cglib代理的时候使用，正常情况下不需要使用
     */
    public ValidHeadBodyListener() {

    }

    public ValidHeadBodyListener(Class<T> excelType) {
        super(excelType, new ExcelEntity<>());
        validHeadListener = new ValidHeadListener<>(excelType, excelEntity);
        validBodyListener = new ValidBodyListener<>(excelType, excelEntity);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        validHeadListener.invokeHeadMap(headMap, context);
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        // context.readWorkBookHolder().setIgnoreEmptyRow(false) 默认为true，现在导入遇到空行会出现行号提示错乱，暂不处理
        // 如果模板校验有问题，不做内容处理
        if (!validHeadListener.templateValidThrow && StringUtils.hasText(validHeadListener.headErrorMsg)) {
            return;
        }
        validBodyListener.invoke(data, context);
    }

    public void setTemplateValidThrow(boolean isThrow) {
        validHeadListener.setTemplateValidThrow(isThrow);
    }

    public String getHeadErrorMsg() {
        return validHeadListener.getHeadErrorMsg();
    }

    public String getArrErrorMsg() {
        return validBodyListener.getBodyErrorMsg();
    }

    public String getArrErrorMsg(boolean isNeedShowSheetName) {
        return validBodyListener.getBodyErrorMsg(isNeedShowSheetName);
    }

    public void validBodyRepeatEmpty(Function<T, String> getUk) {
        validBodyListener.validBodyRepeatEmpty(getUk);
    }

    @Override
    public void validBody() {
        validBodyListener.validBody();
    }

    @Override
    public void validEmpty() {
        validBodyListener.validEmpty();
    }
}
