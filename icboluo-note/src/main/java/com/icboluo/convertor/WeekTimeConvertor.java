package com.icboluo.convertor;


import com.icboluo.common.Constant;
import com.icboluo.object.dataobject.MonthTimeDO;
import com.icboluo.object.dataobject.TimeNoteDO;
import com.icboluo.object.dataobject.WeekTimeDO;
import com.icboluo.object.viewobject.NoteVO;
import com.icboluo.util.BeanHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author icboluo
 */
@Component
public class WeekTimeConvertor implements Convertor<WeekTimeDO, WeekTimeDO, WeekTimeDO, NoteVO> {

    public MonthTimeDO toMonth(WeekTimeDO weekTimeDO) {
        MonthTimeDO monthTimeDO = BeanHelper.copyProperties(weekTimeDO, MonthTimeDO.class);
        monthTimeDO.setId(null);
        monthTimeDO.setGmtCreate(null);
        monthTimeDO.setGmtModified(new Date());
        monthTimeDO.setFinishTime(0);
        return monthTimeDO;
    }

    public TimeNoteDO toTime(WeekTimeDO weekTimeDO) {
        TimeNoteDO timeNoteDO = BeanHelper.copyProperties(weekTimeDO, TimeNoteDO.class);
        timeNoteDO.setId(null);
        timeNoteDO.setFinishTime(Constant.TIME_NOT_FINISH_TIME);
        timeNoteDO.setGmtCreate(weekTimeDO.getGmtFirstCreate());
        timeNoteDO.setGmtModified(null);
        return timeNoteDO;
    }

    @Override
    public NoteVO toView(WeekTimeDO weekTimeDO) {
        NoteVO noteVO = BeanHelper.copyProperties(weekTimeDO, NoteVO.class);
        noteVO.setType(Constant.WEEK_TYPE);
        return noteVO;
    }
}
