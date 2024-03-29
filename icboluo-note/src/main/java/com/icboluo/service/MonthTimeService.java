package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.entity.note.MonthTimeDO;
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

    public MonthTimeDO getUpdateObj(Integer id, Integer isFinish) {
        MonthTimeDO monthTime = monthTimeMapper.selectByPrimaryKey(id);
        MonthTimeDO monthTimeDO = new MonthTimeDO();
        monthTimeDO.setId(id);
        if (isFinish == Constant.NOT_FINISH) {
            monthTimeDO.setFinishTime(monthTime.getFinishTime() - 1);
        }
        return monthTimeDO;
    }
}
