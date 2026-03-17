package com.icboluo.service;

import com.icboluo.entity.BlacklistCo;

import java.util.List;

public interface SystemConfigService {
    List<BlacklistCo> selectBlacklist();
}
