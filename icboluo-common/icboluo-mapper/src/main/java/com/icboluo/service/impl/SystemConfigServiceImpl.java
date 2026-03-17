package com.icboluo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icboluo.common.SystemConfigEnum;
import com.icboluo.entity.BlacklistCo;
import com.icboluo.entity.SystemConfig;
import com.icboluo.mapper.SystemConfigMapper;
import com.icboluo.service.SystemConfigService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SystemConfigServiceImpl implements SystemConfigService {
    private final SystemConfigMapper systemConfigMapper;

    private final ObjectMapper om = new ObjectMapper();

    @SneakyThrows
    @Override
    public List<BlacklistCo> selectBlacklist() {
        SystemConfig systemConfig = systemConfigMapper.selectById(SystemConfigEnum.BLACK_LIST.name());
        String value = Optional.ofNullable(systemConfig).map(SystemConfig::getConfigKey).orElse(null);
        List<BlacklistCo> strList;
        if (value == null) {
            strList = new ArrayList<>();
        } else {
            strList = om.readValue(value, om.getTypeFactory().constructParametricType(List.class, BlacklistCo.class));
        }
        return strList;
    }
}
