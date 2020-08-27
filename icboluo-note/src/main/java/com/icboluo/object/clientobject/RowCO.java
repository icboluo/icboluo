package com.icboluo.object.clientobject;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author icboluo
 */
@Data
public class RowCO {
    /**
     * 列名
     */
    @ExcelProperty(value = "列名")
    private String columnName;

    @ExcelProperty(value = "列名")
    private String columnName2;
    /**
     * 数据类型
     */
    @ExcelProperty(value = "字段类型长度")
    private String columnType;
    /**
     * 字段类型
     */
    @ExcelProperty(value = "字段类型")
    private String dataType;
    /**
     * 长度
     */
    @ExcelProperty(value = "长度")
    private String characterMaximumLength;
    /**
     * 是否为空 0：不能 1：能
     */
    @ExcelProperty(value = "是否为空")
    private String isNullable;
    /**
     * 默认值
     */
    @ExcelProperty(value = "默认值")
    private String columnDefault;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String columnComment;
    /**
     * 是否主键
     */
    @ExcelProperty(value = "是否主键")
    private String isPrimaryKey;

    public boolean isPrimaryKey(String isPrimaryKey) {
        if (StringUtils.isEmpty(isPrimaryKey)) {
            return false;
        }
        return switch (isPrimaryKey) {
            case "是", "1", "YES", "PRI" -> true;
            case "否", "0", "NO" -> false;
            default -> throw new IllegalStateException("Unexpected value: " + isPrimaryKey);
        };
    }

    public boolean isNull(String isNullAble) {
        if (StringUtils.isEmpty(isNullAble)) {
            return false;
        }
        return switch (isNullAble) {
            case "是", "1", "YES" -> true;
            case "否", "0", "NO" -> false;
            default -> throw new IllegalStateException("Unexpected value: " + isNullAble);
        };
    }

}
