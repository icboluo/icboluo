package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.icboluo.annotation.Date;
import com.icboluo.annotation.ExcelExport;
import com.icboluo.util.ValidateUtil;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-08-03 21:56
 */
public class ValidHeadBodyListener<T> extends ValidHeadExcelListener<T> {
    public ValidHeadBodyListener(Class<T> excelType, int headNum) {
        super(excelType, headNum);
    }

    @Getter
    private String[][] arr;

    private int rowNum = 0;

    @Override
    public void invoke(T data, AnalysisContext context) {
        if (arr == null) {
            // 这个近似总行数会统计空行，目前没有遇到过近似行数不准确的情况
            arr = new String[context.readSheetHolder().getApproximateTotalRowNumber()][8];
        }
        Field[] fields = clazz.getDeclaredFields();
        boolean rowValidFlag = true;
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Set<ConstraintViolation<T>> constraintViolations = ValidateUtil.validateProperty(data, name);
            String msg;
            if (CollectionUtils.isEmpty(constraintViolations)) {
                msg = validateOther(field, data);
            } else {
                List<ConstraintViolation<T>> collect = constraintViolations.stream().sorted().toList();
                msg = collect.get(0).getMessage();
            }
            if (StringUtils.hasText(msg)) {
                ExcelExport ep = field.getAnnotation(ExcelExport.class);
                arr[rowNum + head][ep.columnIndex()] = msg;
                rowValidFlag = false;
            }
        }
        List<String> strings = ValidateUtil.validateProperty(data);
        if (rowValidFlag) {
            super.invoke(data, context);
        }
        rowNum++;
    }

    @SneakyThrows(IllegalAccessException.class)
    private static <T> String validateOther(Field field, T row) {
        if (field.isAnnotationPresent(Date.class)) {
            Object o = field.get(row);
            if (o == null) {
                return null;
            }
            if (o instanceof LocalDate) {
                return null;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                format.parse((String) o);
            } catch (ParseException e) {
                return "date error";
            }
        }
        return null;
    }
}
