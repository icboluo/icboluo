package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.common.redis.RedisList;
import com.icboluo.entity.base.Unit;
import com.icboluo.mapper.UnitMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


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
    private RedisList<Unit> redisList;

    public List<Unit> getAll() {
        List<Unit> list = redisList.rCap(
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
    public List<Unit> selectByCode(String code) {
        String key = Constant.UNIT + ":" + code;
        List<Unit> list = redisList.rCap(
                () -> redisList.get(key),
                () -> unitMapper.selectByCode(code),
                db -> redisList.addAll(key, db)
        );
        System.out.println(list);
        return list;
    }
}
