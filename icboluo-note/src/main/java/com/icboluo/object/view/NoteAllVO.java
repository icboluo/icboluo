package com.icboluo.object.view;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author icboluo
 */
@Data
@NoArgsConstructor
public class NoteAllVO {

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

    /**
     * 应该完成时间
     */
    private long shouldFinishTime;

    /**
     * 每天剩余数量
     */
    private int timeNoteAmount;
    /**
     * 每周剩余数量
     */
    private int weekTimeAmount;
    /**
     * 每月剩余数量
     */
    private int monthTimeAmount;
    /**
     * 所属类型
     */
    private String type;
}
