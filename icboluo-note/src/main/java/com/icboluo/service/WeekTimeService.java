package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.entity.NoteWeekTime;
import com.icboluo.mapper.WeekTimeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author icboluo
 */
@Service
public class WeekTimeService {
    @Resource
    private WeekTimeMapper weekTimeMapper;

    public NoteWeekTime getUpdateObj(Integer id, Integer isFinish) {
        NoteWeekTime noteWeekTime = weekTimeMapper.selectByPrimaryKey(id);
        if (isFinish == Constant.NOT_FINISH) {
            noteWeekTime.setFinishTime(noteWeekTime.getFinishTime() - 1);
        }
        noteWeekTime.setGmtModified(null);
        return noteWeekTime;
    }
}
