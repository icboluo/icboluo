package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.dao.TimeNoteMapper;
import com.icboluo.object.dataobject.TimeNoteDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author icboluo
 */
@Service
public class TimeNoteService {
    @Resource
    private TimeNoteMapper timeNoteMapper;

    public TimeNoteDO getUpdateObj(Integer id, Integer isFinish) {
        TimeNoteDO timeNote = timeNoteMapper.selectByPrimaryKey(id);
        TimeNoteDO timeNoteDO = new TimeNoteDO();
        if (isFinish == Constant.NOT_FINISH) {
            timeNoteDO.setFinishTime(Math.max(timeNote.getFinishTime() - 1, 0));
        }
        timeNoteDO.setId(id);
        timeNoteDO.setGmtModified(new Date());
        return timeNoteDO;
    }
}
