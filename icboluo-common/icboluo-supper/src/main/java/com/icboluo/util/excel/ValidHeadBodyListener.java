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

/**
 * 表头、表内容校验
 *
 * @author icboluo
 * @since 2023-08-03 21:56
 */
public class ValidHeadBodyListener<T> extends ValidHeadListener<T> {

    /**
     * 记录多行错误消息，错误消息的位置和Excel完全匹配
     */
    private final List<String[]> msgList = new ArrayList<>();

    /**
     * 无参构造，cglib代理的时候使用，正常情况下不需要使用
     */
    public ValidHeadBodyListener() {

    }

    public ValidHeadBodyListener(Class<T> excelType) {
        super(excelType);
    }

    public ValidHeadBodyListener(Class<T> excelType, int headNum) {
        super(excelType, headNum);
    }


    @Override
    public void invoke(T data, AnalysisContext context) {
        // 如果模版校验有问题，不做内容处理
        if (!isTemplateValidThrow && StringUtils.hasText(headErrorMsg)) {
            return;
        }
        // 这个近似总行数会统计空行，目前没有遇到过近似行数不准确的情况(目前不需要
        context.readSheetHolder().getApproximateTotalRowNumber();
        if (msgList.isEmpty()) {
            for (int i = 0; i < head; i++) {
                msgList.add(new String[getMaxLine() + 1]);
            }
        }
        msgList.add(new String[getMaxLine() + 1]);
        Field[] fields = clazz.getDeclaredFields();
        Map<Field, String> msgMap = ValidateUtil.validateFieldsToMap(data, fields);
        for (Map.Entry<Field, String> entry : msgMap.entrySet()) {
            Excel excel = entry.getKey().getAnnotation(Excel.class);
            msgList.get(msgList.size() - 1)[excel.columnIndex()] = entry.getValue();
        }
        if (msgMap.isEmpty()) {
            list.add(data);
        }
    }

    public String getErrorMsg() {
        return getErrorMsg(false);
    }

    public String getErrorMsg(boolean isNeedShowSheetName) {
        List<String> msg = new ArrayList<>();
        for (int i = 0; i < msgList.size(); i++) {
            for (int j = 0; j < msgList.get(i).length; j++) {
                if (msgList.get(i)[j] != null) {
                    String message = MESSAGE_SOURCE.getMessage("row.{0}.col.{1}.error.{2}",
                            new Object[]{i + 1, ExcelUtil.convertToTitle(j + 1), msgList.get(i)[j]},
                            LocaleContextHolder.getLocale());
                    msg.add(message);
                }
            }
        }
        if (msg.isEmpty()) {
            return null;
        }
        if (isNeedShowSheetName) {
            return "[" + sheetName + "]:<br/>" + String.join(";<br/>", msg);
        } else {
            return String.join(";<br/>", msg);
        }
    }
}
