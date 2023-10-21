package com.icboluo.object.client;

import lombok.Data;

/**
 * @author lp
 */
@Data
public class TimeNoteCO {

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
}
