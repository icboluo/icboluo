package com.icboluo.object.client;

import lombok.Data;

import java.util.Date;

/**
 * @author lp
 */
@Data
public class WeekTimeCO {

    /**
     * 所属范围
     */
    private String belongToScope;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 问题
     */
    private String problem;
    /**
     * 完成次数
     */
    private Integer finishTime;
    /**
     * 结果
     */
    private String result;

}
