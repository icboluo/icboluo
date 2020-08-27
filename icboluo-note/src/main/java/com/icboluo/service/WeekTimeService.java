package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.dao.WeekTimeMapper;
import com.icboluo.object.dataobject.WeekTimeDO;
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
