package com.icboluo.service;

import com.icboluo.object.bo.I18ParamBO;
import com.icboluo.object.bo.I18Res;

/**
 * @author icboluo
 * @date 2021-44-05 14:44
 */
public interface I18Service {

    <S> I18Res convert(I18ParamBO<S> i18Param);
}
