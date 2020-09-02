package com.icboluo.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author icboluo
 */
@ConfigurationProperties(prefix = "read-excel-entity")
@Data
public class ReadExcelEntity {
    /**
     * excel本地完整路径
     */
    private String excelPath;
    /**
     * 生成建表语句的完整路径
     */
    private String generalSqlPath;
    /**
     * excel的sheet名称
     */
    private String sheetName;

}
