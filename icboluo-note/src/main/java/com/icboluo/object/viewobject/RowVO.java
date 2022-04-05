package com.icboluo.object.viewobject;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author icboluo
 * @since 2020/6/19 9:34
 */
@Data
public class RowVO {
    /**
     * 列名
     */
    @ExcelProperty(value = "列名")
    private String columnName;

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
     *字段类型长度
     */
    @ExcelProperty(value = "字段类型长度")
    private String columnType;
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
    private String columnKey;
}
