package com.icboluo.convertor;


import com.icboluo.common.Constant;
import com.icboluo.common.Convertor;
import com.icboluo.object.dataobject.MonthTimeDO;
import com.icboluo.object.dataobject.WeekTimeDO;
import com.icboluo.object.dataobject.YearTimeDO;
import com.icboluo.object.viewobject.NoteVO;
import com.icboluo.util.BeanHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author icboluo
 */
@Component
public class MonthTimeConvertor implements Convertor<MonthTimeDO, MonthTimeDO, MonthTimeDO, NoteVO> {

    public YearTimeDO toYear(MonthTimeDO monthTime) {
        YearTimeDO yearTimeDO = BeanHelper.copyProperties(monthTime, YearTimeDO.class);
        yearTimeDO.setId(null);
        yearTimeDO.setGmtCreate(null);
        yearTimeDO.setGmtModified(LocalDateTime.now());
        yearTimeDO.setFinishTime(0);
        return yearTimeDO;
    }

    public WeekTimeDO toWeek(MonthTimeDO monthTimeDO) {
        WeekTimeDO weekTimeDO = BeanHelper.copyProperties(monthTimeDO, WeekTimeDO.class);
        weekTimeDO.setId(null);
        weekTimeDO.setGmtCreate(null);
        weekTimeDO.setFinishTime(Constant.WEEK_NOT_FINISH_TIME);
        weekTimeDO.setGmtModified(null);
        return weekTimeDO;
    }

    @Override
    public NoteVO toView(MonthTimeDO monthTimeDO) {
        NoteVO noteVO = BeanHelper.copyProperties(monthTimeDO, NoteVO.class);
        noteVO.setType(Constant.MONTH_TYPE);
        return noteVO;
    }
}
