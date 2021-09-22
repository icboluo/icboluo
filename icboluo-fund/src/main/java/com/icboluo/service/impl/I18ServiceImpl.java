package com.icboluo.service.impl;

import com.icboluo.object.bo.I18ParamBO;
import com.icboluo.object.bo.I18Res;
import com.icboluo.service.I18Service;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author icboluo
 * @date 2021-44-05 14:44
 */
@Service
public class I18ServiceImpl implements I18Service {
    @Override
    public <S> I18Res convert(I18ParamBO<S> i18Param) {
        Function<S, String> findNameByCode = i18Param.getFindNameByCode();
        Set<String> codeSet = new HashSet<>();
        for (S source : i18Param.getSourceList()) {
            String code = findNameByCode.apply(source);
            codeSet.add(code);
        }
        Map<String, String> codeNameMap = nameByCode(codeSet);
        return I18Res.builder()
                .codeNameMap(codeNameMap)
                .build();
    }

    private Map<String, String> nameByCode(Set<String> codeSet) {
        return new HashMap<>();
    }
}
