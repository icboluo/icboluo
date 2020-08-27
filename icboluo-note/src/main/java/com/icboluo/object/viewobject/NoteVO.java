package com.icboluo.object.viewobject;

import com.icboluo.object.dataobject.TimeNoteDO;
import lombok.Data;

/**
 * @author icboluo
 */
@Data
public class NoteVO extends TimeNoteDO {
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
