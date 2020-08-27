package com.icboluo.object.dataobject;

import lombok.Data;

/**
 * @author icboluo
 */
@Data
public class RowDO {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 数据类型
     */
    private String columnType;
    /**
     * 字段类型
     */
    private String dataType;
    /**
     * 长度
     */
    private String characterMaximumLength;
    /**
     * 是否为空 0：不能 1：能
     */
    private String isNullable;
    /**
     * 默认值
     */
    private String columnDefault;
    /**
     * 备注
     */
    private String columnComment;
    /**
     * 是否主键
     */
    private String columnKey;

}
