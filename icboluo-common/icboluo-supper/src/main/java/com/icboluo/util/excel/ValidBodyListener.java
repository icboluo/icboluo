package com.icboluo.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.icboluo.annotation.Excel;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.I18nException;
import com.icboluo.util.ValidateUtil;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 表头校验
 *
 * @param <T>
 */
public class ValidBodyListener<T> extends ExcelListener<T> {
    /**
     * 记录多行错误消息，错误消息的位置和Excel完全匹配
     */
    private final List<String[]> msgList = new ArrayList<>();
    private final List<String> rowMsgList = new ArrayList<>();
    protected boolean isEmpty = true;

    /**
     * 无参构造，cglib代理的时候使用，正常情况下不需要使用
     */
    public ValidBodyListener() {

    }

    public ValidBodyListener(Class<T> excelType) {
        super(excelType, new ExcelEntity<>());
    }

    public ValidBodyListener(Class<T> excelType, ExcelEntity<T> entity) {
        super(excelType, entity);
    }

    public void invoke(T data, AnalysisContext context) {
        // 这个近似总行数会统计空行，目前没有遇到过近似行数不准确的情况(目前不需要
        context.readSheetHolder().getApproximateTotalRowNumber();
        // if只会执行一次
        if (msgList.isEmpty()) {
            isEmpty = false;
            for (int i = 0; i < excelEntity.headRowNumber; i++) {
                msgList.add(new String[getMaxLine() + 1]);
                rowMsgList.add(null);
            }
        }
        msgList.add(new String[getMaxLine() + 1]);
        Field[] fields = excelEntity.clazz.getDeclaredFields();
        Map<Field, String> msgMap = ValidateUtil.validateFieldsToMap(data, fields);
        if (data instanceof RowValidate rowValidate) {
            String rowError = rowValidate.afterFieldValidate();
            rowMsgList.add(rowError);
        } else {
            rowMsgList.add(null);
        }
        for (Map.Entry<Field, String> entry : msgMap.entrySet()) {
            Excel excel = entry.getKey().getAnnotation(Excel.class);
            msgList.get(msgList.size() - 1)[excel.columnIndex()] = entry.getValue();
        }
        if (msgMap.isEmpty()) {
            excelEntity.list.add(data);
        }
    }

    public String getBodyErrorMsg() {
        return getBodyErrorMsg(false);
    }

    public String getBodyErrorMsg(boolean needShowSheetName) {
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
            if (StringUtils.hasText(rowMsgList.get(i))) {
                String rowMessage = MESSAGE_SOURCE.getMessage("row.{0}.error.{1}",
                        new Object[]{i + 1, rowMsgList.get(i)}, LocaleContextHolder.getLocale());
                msg.add(rowMessage);
            }
        }
        if (msg.isEmpty()) {
            return null;
        }
        if (needShowSheetName) {
            return "[" + excelEntity.sheetName + "]:<br/>" + String.join(";<br/>", msg);
        } else {
            return String.join(";<br/>", msg);
        }
    }

    public void validBodyRepeatEmpty(Function<T, String> getUk) {
        this.validBody();
        super.validRepeat(getUk);
        this.validEmpty();
    }

    @Override
    public void validBody() {
        String msg = getBodyErrorMsg();
        if (StringUtils.hasText(msg)) {
            throw new I18nException(msg);
        }
    }

    @Override
    public void validBody(boolean needShowSheetName) {
        String msg = getBodyErrorMsg(needShowSheetName);
        if (StringUtils.hasText(msg)) {
            throw new I18nException(msg);
        }
    }

    @Override
    public void validEmpty() {
        if (isEmpty) {
            throw new I18nException(ReEnum.EXCEL_DATA_IS_EMPTY);
        }
    }
}
