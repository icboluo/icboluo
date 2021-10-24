package com.icboluo.object.dataobject;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author
 *
 */
@Data
public class TimeNoteDO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 问题
     */
    private String problem;

    /**
     * 结果
     */
    private String result;

    /**
     * 完成次数
     */
    private Integer finishTime;

    /**
     * 所属范围
     */
    private String belongToScope;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    @Serial
    private static final long serialVersionUID = 1L;
}
