package com.icboluo.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author icboluo
 * @since 2020/6/18 16:09
 */
@ConfigurationProperties(prefix = "write-excel-entity")
@Data
public class WriteExcelEntity {
    /**
     * 写excel的完整路径
     */
    private String excelPath;
    /**1
     * 数据库名称
     */
    private String database;
    /**
     * 表名称
     */
    private String tableName;
}
