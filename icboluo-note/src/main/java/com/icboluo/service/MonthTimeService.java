package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.entity.NoteMonthTime;
import com.icboluo.mapper.MonthTimeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author icboluo
 */
@Service
public class MonthTimeService {
    @Resource
    private MonthTimeMapper monthTimeMapper;

    public NoteMonthTime getUpdateObj(Integer id, Integer isFinish) {
        NoteMonthTime monthTime = monthTimeMapper.selectByPrimaryKey(id);
        NoteMonthTime noteMonthTime = new NoteMonthTime();
        noteMonthTime.setId(id);
        if (isFinish == Constant.NOT_FINISH) {
            noteMonthTime.setFinishTime(monthTime.getFinishTime() - 1);
        }
        return noteMonthTime;
    }
}
