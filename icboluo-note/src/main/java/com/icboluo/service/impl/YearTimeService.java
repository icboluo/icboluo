package com.icboluo.service.impl;

import com.icboluo.common.Constant;
import com.icboluo.mapper.YearTimeMapper;
import com.icboluo.object.dataobject.YearTimeDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author icboluo
 */
@Service
public class YearTimeService {
    @Resource
    private YearTimeMapper yearTimeMapper;

    public YearTimeDO getUpdateObj(Integer id, Integer isFinish) {
        YearTimeDO yearTime = yearTimeMapper.selectByPrimaryKey(id);
        YearTimeDO yearTimeDO = new YearTimeDO();
        if (isFinish == Constant.NOT_FINISH) {
            yearTimeDO.setFinishTime(yearTime.getFinishTime() - 1);
        }
        yearTimeDO.setId(id);
        return yearTimeDO;
    }
}
