package com.icboluo.service.impl;

import com.icboluo.object.bo.I18ParamBO;
import com.icboluo.object.bo.I18Res;
import com.icboluo.service.I18Service;
import org.springframework.stereotype.Service;

/**
 * @author icboluo
 * @date 2021-44-05 14:44
 */
@Service
public class I18ServiceImpl implements I18Service {
    @Override
    public <S> I18Res convert(I18ParamBO<S> i18Param) {
        return I18Res.builder()
                .build();
    }
}
