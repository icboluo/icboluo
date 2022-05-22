package com.icboluo.object.view;

import lombok.Data;

/**
 * 根据字段查询的结果集对象
 *
 * @author icboluo
 */
@Data
public class FiledResultVO {
    /**
     * id
     */
    private Integer id;
    /**
     * 所属范围
     */
    private String belongToScope;
    /**
     * 问题
     */
    private String problem;
    /**
     * 结果
     */
    private String result;
    /**
     * 类型
     */
    private String type;
}
