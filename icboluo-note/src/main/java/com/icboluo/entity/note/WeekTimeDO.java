package com.icboluo.entity.note;

import com.icboluo.common.Constant;
import com.icboluo.object.view.FiledResultVO;
import com.icboluo.object.view.NoteVO;
import com.icboluo.util.BeanHelper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author icboluo
 */
@Data
public class WeekTimeDO implements NoteViewInter, FiledResultInter, NoteCommonInter, Serializable {
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
        NoteVO noteVO = BeanHelper.copyProperties(this, NoteVO.class);
        noteVO.setType(Constant.WEEK_TYPE);
        return noteVO;
    }

    @Override
    public FiledResultVO toFiledResultView() {
        FiledResultVO filedResult = BeanHelper.copyProperties(this, FiledResultVO.class);
        filedResult.setType(Constant.WEEK_TYPE);
        return filedResult;
    }

    public MonthTimeDO toMonth() {
        MonthTimeDO monthTimeDO = BeanHelper.copyProperties(this, MonthTimeDO.class);
        monthTimeDO.setId(null);
        monthTimeDO.setGmtCreate(null);
        monthTimeDO.setGmtModified(LocalDateTime.now());
        monthTimeDO.setFinishTime(0);
        return monthTimeDO;
    }

    public TimeNoteDO toTime() {
        TimeNoteDO timeNoteDO = BeanHelper.copyProperties(this, TimeNoteDO.class);
        timeNoteDO.setId(null);
        timeNoteDO.setFinishTime(Constant.TIME_NOT_FINISH_TIME);
        timeNoteDO.setGmtCreate(this.getGmtFirstCreate());
        timeNoteDO.setGmtModified(null);
        return timeNoteDO;
    }
}
