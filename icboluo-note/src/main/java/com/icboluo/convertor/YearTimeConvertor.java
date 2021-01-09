package com.icboluo.convertor;

import com.icboluo.common.Constant;
import com.icboluo.common.Convertor;
import com.icboluo.object.dataobject.FinishDO;
import com.icboluo.object.dataobject.MonthTimeDO;
import com.icboluo.object.dataobject.YearTimeDO;
import com.icboluo.object.viewobject.NoteVO;
import com.icboluo.util.BeanHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author icboluo
 */
@Component
public class YearTimeConvertor implements Convertor<YearTimeDO, YearTimeDO, YearTimeDO, NoteVO> {

    public FinishDO toFinish(YearTimeDO yearTimeDO) {
        FinishDO finishDO = BeanHelper.copyProperties(yearTimeDO, FinishDO.class);
        finishDO.setId(null);
        finishDO.setGmtCreate(null);
        finishDO.setGmtModified(LocalDateTime.now());
        return finishDO;

    }

    public MonthTimeDO toMonth(YearTimeDO yearTimeDO) {
        MonthTimeDO monthTimeDO = BeanHelper.copyProperties(yearTimeDO, MonthTimeDO.class);
        monthTimeDO.setId(null);
        monthTimeDO.setGmtCreate(null);
        monthTimeDO.setFinishTime(Constant.MONTH_NOT_FINISH_TIME);
        monthTimeDO.setGmtModified(null);
        return monthTimeDO;
    }

    @Override
    public NoteVO toView(YearTimeDO yearTimeDO) {
        NoteVO noteVO = BeanHelper.copyProperties(yearTimeDO, NoteVO.class);
        noteVO.setType(Constant.YEAR_TYPE);
        return noteVO;
    }
}
