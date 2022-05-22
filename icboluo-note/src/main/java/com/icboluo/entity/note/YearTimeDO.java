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
public class YearTimeDO implements NoteViewInter, FiledResultInter, NoteCommonInter, Serializable {
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
        noteVO.setType(Constant.YEAR_TYPE);
        return noteVO;
    }

    @Override
    public FiledResultVO toFiledResultView() {
        FiledResultVO filedResult = BeanHelper.copyProperties(this, FiledResultVO.class);
        filedResult.setType(Constant.YEAR_TYPE);
        return filedResult;
    }

    public FinishDO toFinish() {
        FinishDO finishDO = BeanHelper.copyProperties(this, FinishDO.class);
        finishDO.setId(null);
        finishDO.setGmtCreate(null);
        finishDO.setGmtModified(LocalDateTime.now());
        return finishDO;

    }

    public MonthTimeDO toMonth() {
        MonthTimeDO monthTimeDO = BeanHelper.copyProperties(this, MonthTimeDO.class);
        monthTimeDO.setId(null);
        monthTimeDO.setGmtCreate(null);
        monthTimeDO.setFinishTime(Constant.MONTH_NOT_FINISH_TIME);
        monthTimeDO.setGmtModified(null);
        return monthTimeDO;
    }
}
