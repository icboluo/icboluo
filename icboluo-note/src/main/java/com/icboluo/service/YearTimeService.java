package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.entity.NoteYearTime;
import com.icboluo.mapper.YearTimeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author icboluo
 */
@Service
public class YearTimeService {
    @Resource
    private YearTimeMapper yearTimeMapper;

    public NoteYearTime getUpdateObj(Integer id, Integer isFinish) {
        NoteYearTime yearTime = yearTimeMapper.selectByPrimaryKey(id);
        NoteYearTime noteYearTime = new NoteYearTime();
        if (isFinish == Constant.NOT_FINISH) {
            noteYearTime.setFinishTime(yearTime.getFinishTime() - 1);
        }
        noteYearTime.setId(id);
        return noteYearTime;
    }
}
