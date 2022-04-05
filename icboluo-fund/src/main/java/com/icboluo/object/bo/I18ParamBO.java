package com.icboluo.object.bo;

import lombok.Data;

import java.util.List;
import java.util.function.Function;

/**
 * @author icboluo
 * @since 2021-45-05 14:45
 */
@Data
public class I18ParamBO<S> {

    private Function<S,String> findNameByCode;

    private List<S> sourceList;

    public I18ParamBO(List<S> source) {
        sourceList = source;
    }
}
