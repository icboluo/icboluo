package com.icboluo.util.convertor;


import com.icboluo.common.Constant;
import com.icboluo.common.Convertor;
import com.icboluo.object.dataobject.TimeNoteDO;
import com.icboluo.object.dataobject.WeekTimeDO;
import com.icboluo.object.viewobject.NoteVO;
import com.icboluo.util.BeanHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author icboluo
 */
@Component
public class TimeNoteConvertor implements Convertor<TimeNoteDO, TimeNoteDO, TimeNoteDO, NoteVO> {

    public WeekTimeDO toWeek(TimeNoteDO timeNoteDO) {
        WeekTimeDO weekTimeDO = BeanHelper.copyProperties(timeNoteDO, WeekTimeDO.class);
        weekTimeDO.setId(null);
        weekTimeDO.setGmtCreate(null);
        weekTimeDO.setGmtFirstCreate(timeNoteDO.getGmtCreate());
        weekTimeDO.setGmtModified(LocalDateTime.now());
        weekTimeDO.setFinishTime(0);
        return weekTimeDO;
    }

    @Override
    public NoteVO toView(TimeNoteDO timeNoteDO) {
        NoteVO noteVO = BeanHelper.copyProperties(timeNoteDO, NoteVO.class);
        noteVO.setType(Constant.TIME_TYPE);
        return noteVO;
    }
}
