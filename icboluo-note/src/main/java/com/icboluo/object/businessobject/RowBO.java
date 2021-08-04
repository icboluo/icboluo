package com.icboluo.object.businessobject;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author icboluo
 */
@Data
public class RowBO {
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
    private String isPrimaryKey;

    public static boolean isPrimaryKey(String isPrimaryKey) {
        if (StringUtils.hasText(isPrimaryKey)) {
            return false;
        }
        return switch (isPrimaryKey) {
            case "是", "1", "YES", "PRI" -> true;
            case "否", "0", "NO", "MUL", "UNI" -> false;
            default -> throw new IllegalStateException("Unexpected value: " + isPrimaryKey);
        };
    }

    public boolean isNull(String isNullAble) {
        if (StringUtils.hasText(isNullAble)) {
            return false;
        }
        return switch (isNullAble) {
            case "是", "1", "YES" -> true;
            case "否", "0", "NO" -> false;
            default -> throw new IllegalStateException("Unexpected value: " + isNullAble);
        };
    }
}
