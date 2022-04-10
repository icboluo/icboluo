package com.icboluo.util.listenter;

import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-04-05 18:58
 */
public abstract class ExcelListener<T> extends AnalysisEventListener<T> {

    @Getter
    private final List<T> list = new ArrayList<>();

    /**
     * 获取头行数，用于Excel分析
     *
     * @return 头行数
     */
    public abstract int head();
}
