package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.mapper.UnitMapper;
import com.icboluo.object.dataobject.UnitDO;
import com.icboluo.util.redis.ListRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author icboluo
 * @date 2021-08-01 22:08
 */
@Service
public class UnitService {

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private ListRedis<UnitDO> listRedis;

    public List<UnitDO> getAll() {
        List<UnitDO> list = listRedis.rCap(
                () -> listRedis.get(Constant.unit),
                unitMapper::selectAll,
                db -> listRedis.addAll(Constant.unit, db)
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
        String key = Constant.unit + ":" + code;
        List<UnitDO> list = listRedis.rCap(
                () -> listRedis.get(key),
                () -> unitMapper.selectByCode(code),
                db -> listRedis.addAll(key, db)
        );
        System.out.println(list);
        return list;
    }
}
