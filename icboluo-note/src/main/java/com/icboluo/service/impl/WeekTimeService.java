package com.icboluo.service.impl;

import com.icboluo.common.Constant;
import com.icboluo.mapper.WeekTimeMapper;
import com.icboluo.entity.note.WeekTimeDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author icboluo
 */
@Service
public class WeekTimeService {
    @Resource
    private WeekTimeMapper weekTimeMapper;

    public WeekTimeDO getUpdateObj(Integer id, Integer isFinish) {
        WeekTimeDO weekTimeDO = weekTimeMapper.selectByPrimaryKey(id);
        if (isFinish == Constant.NOT_FINISH) {
            weekTimeDO.setFinishTime(weekTimeDO.getFinishTime() - 1);
        }
        weekTimeDO.setGmtModified(null);
        return weekTimeDO;
    }
}
