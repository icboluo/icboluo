package com.icboluo.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author icboluo
 */
@ConfigurationProperties("read-excel-entity")
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
