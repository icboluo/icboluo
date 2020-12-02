package com.icboluo.object.clientobject;

import com.alibaba.excel.annotation.ExcelProperty;
import com.icboluo.annotation.EasyExcelAlias;
import lombok.Data;

/**
 * @author icboluo
 * @date 2020/12/2 21:02
 */
@Data
public class NoModelRowCO {
    /**
     * 列名
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String columnName;
    /**
     * 数据类型
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String columnType;
    /**
     * 字段类型
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String dataType;
    /**
     * 长度
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String characterMaximumLength;
    /**
     * 是否为空 0：不能 1：能
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String isNullable;
    /**
     * 默认值
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String columnDefault;
    /**
     * 备注
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String columnComment;
    /**
     * 是否主键
     */
    @ExcelProperty
    @EasyExcelAlias(alias = "列名")
    private String isPrimaryKey;

}
