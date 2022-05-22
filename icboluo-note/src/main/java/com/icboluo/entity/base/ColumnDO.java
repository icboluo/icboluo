package com.icboluo.entity.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author
 *
 */
@Data
public class ColumnDO implements Serializable {
    private String tableCatalog;

    private String tableSchema;

    private String tableName;

    private String columnName;

    private Long ordinalPosition;

    private String isNullable;

    private String dataType;

    private Long characterMaximumLength;

    private Long characterOctetLength;

    private Long numericPrecision;

    private Long numericScale;

    private Long datetimePrecision;

    private String characterSetName;

    private String collationName;

    private String columnKey;

    private String extra;

    private String privileges;

    private String columnComment;

    @Serial
    private static final long serialVersionUID = 1L;
}
