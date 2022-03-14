package com.icboluo.service.impl;

import com.icboluo.common.Constant;
import com.icboluo.mapper.TimeNoteMapper;
import com.icboluo.object.dataobject.TimeNoteDO;
import com.icboluo.service.TimeNoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author icboluo
 */
@Service
public class TimeNoteServiceImpl implements TimeNoteService {
    @Resource
    private TimeNoteMapper timeNoteMapper;

    @Override
    public TimeNoteDO getUpdateObj(Integer id, Integer isFinish) {
        TimeNoteDO timeNote = timeNoteMapper.selectByPrimaryKey(id);
        TimeNoteDO timeNoteDO = new TimeNoteDO();
        if (isFinish == Constant.NOT_FINISH) {
            timeNoteDO.setFinishTime(Math.max(timeNote.getFinishTime() - 1, 0));
        }
        timeNoteDO.setId(id);
        timeNoteDO.setGmtModified(LocalDateTime.now());
        return timeNoteDO;
    }
}