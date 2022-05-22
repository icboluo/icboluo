package com.icboluo.object.business;

import lombok.Data;

import java.util.List;

/**
 * @author icboluo
 */
@Data
public class SheetBO {
    /**
     * 数据库名称
     */
    private String database;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 行数据
     */
    private List<RowBO> list;
}
