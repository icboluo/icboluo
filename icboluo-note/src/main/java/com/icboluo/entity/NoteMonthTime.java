package com.icboluo.entity;

import com.icboluo.common.Constant;
import com.icboluo.entity.inter.FiledResultInter;
import com.icboluo.entity.inter.NoteCommonInter;
import com.icboluo.entity.inter.NoteViewInter;
import com.icboluo.object.view.FiledResultVO;
import com.icboluo.object.view.NoteVO;
import com.icboluo.util.BeanUtil;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author icboluo
 */
@Data
public class NoteMonthTime implements NoteViewInter, FiledResultInter, NoteCommonInter, Serializable {
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
     * 源头数据创建时间
     */
    private LocalDateTime gmtFirstCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public NoteVO toView() {
        NoteVO noteVO = BeanUtil.copyProperties(this, NoteVO.class);
        noteVO.setType(Constant.MONTH_TYPE);
        return noteVO;
    }

    @Override
    public FiledResultVO toFiledResultView() {
        FiledResultVO filedResult = BeanUtil.copyProperties(this, FiledResultVO.class);
        filedResult.setType(Constant.MONTH_TYPE);
        return filedResult;
    }

    public NoteYearTime toYear() {
        NoteYearTime noteYearTime = BeanUtil.copyProperties(this, NoteYearTime.class);
        noteYearTime.setId(null);
        noteYearTime.setGmtCreate(null);
        noteYearTime.setGmtModified(LocalDateTime.now());
        noteYearTime.setFinishTime(0);
        return noteYearTime;
    }

    public NoteWeekTime toWeek() {
        NoteWeekTime noteWeekTime = BeanUtil.copyProperties(this, NoteWeekTime.class);
        noteWeekTime.setId(null);
        noteWeekTime.setGmtCreate(null);
        noteWeekTime.setFinishTime(Constant.WEEK_NOT_FINISH_TIME);
        noteWeekTime.setGmtModified(null);
        return noteWeekTime;
    }
}
