package com.icboluo.service.impl;

import com.icboluo.common.Constant;
import com.icboluo.mapper.UnitMapper;
import com.icboluo.object.dataobject.UnitDO;
import com.icboluo.common.redis.RedisList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author icboluo
 * @since 2021-08-01 22:08
 */
@Service
public class UnitService {

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private RedisList<UnitDO> redisList;

    public List<UnitDO> getAll() {
        List<UnitDO> list = redisList.rCap(
                () -> redisList.get(Constant.UNIT),
                unitMapper::selectAll,
                db -> redisList.addAll(Constant.UNIT, db)
        );
        System.out.println(list);
        return list;
    }

    /**
     * todo 举例不恰当
     *
     * @param code 分组code
     * @return list
     */
    public List<UnitDO> selectByCode(String code) {
        String key = Constant.UNIT + ":" + code;
        List<UnitDO> list = redisList.rCap(
                () -> redisList.get(key),
                () -> unitMapper.selectByCode(code),
                db -> redisList.addAll(key, db)
        );
        System.out.println(list);
        return list;
    }
}
