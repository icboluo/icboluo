package com.icboluo.entity.note;

import com.icboluo.common.Constant;
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
public class MonthTimeDO implements NoteViewInter, FiledResultInter, NoteCommonInter, Serializable {
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

    public YearTimeDO toYear() {
        YearTimeDO yearTimeDO = BeanUtil.copyProperties(this, YearTimeDO.class);
        yearTimeDO.setId(null);
        yearTimeDO.setGmtCreate(null);
        yearTimeDO.setGmtModified(LocalDateTime.now());
        yearTimeDO.setFinishTime(0);
        return yearTimeDO;
    }

    public WeekTimeDO toWeek() {
        WeekTimeDO weekTimeDO = BeanUtil.copyProperties(this, WeekTimeDO.class);
        weekTimeDO.setId(null);
        weekTimeDO.setGmtCreate(null);
        weekTimeDO.setFinishTime(Constant.WEEK_NOT_FINISH_TIME);
        weekTimeDO.setGmtModified(null);
        return weekTimeDO;
    }
}
