package com.icboluo.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.RowTypeEnum;
import com.icboluo.annotation.Excel;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.I18nException;
import com.icboluo.util.ValidateUtil;
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
        var c = context.readSheetHolder().getApproximateTotalRowNumber();
        // if只会执行一次
        if (isEmpty) {
            isEmpty = false;
        }
        ExcelData<T> ed = new ExcelData<>();
        ed.setRowNo(context.readRowHolder().getRowIndex() + excelEntity.headRowNumber);
        // 如果有空行
        if (context.readRowHolder().getRowType() == RowTypeEnum.EMPTY) {
            ed.setRowEmpty(true);
        } else if (data instanceof RowValidate rowValidate) {
            String rowMsg = rowValidate.afterFieldValidate();
            ed.setRowMsg(rowMsg);
        } else {
            Field[] fields = excelEntity.clazz.getDeclaredFields();
            Map<Field, String> msgMap = ValidateUtil.validateFieldsToMap(data, fields);
            if (!msgMap.isEmpty()) {
                String[] msgArr = new String[getMaxLine() + 1];
                for (Map.Entry<Field, String> entry : msgMap.entrySet()) {
                    Excel excel = entry.getKey().getAnnotation(Excel.class);
                    msgArr[excel.columnIndex()] = entry.getValue();
                }
                ed.setCellMsg(msgArr);
            }
        }
        excelEntity.list.add(ed);
    }


    public String getBodyErrorMsg() {
        return getBodyErrorMsg(false);
    }

    public String getBodyErrorMsg(boolean needShowSheetName) {
        List<String> msg = new ArrayList<>();
        for (ExcelData<T> ed : excelEntity.list) {
            msg.addAll(ed.cellError(MESSAGE_SOURCE));
            ed.rowError(MESSAGE_SOURCE).ifPresent(msg::add);
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
