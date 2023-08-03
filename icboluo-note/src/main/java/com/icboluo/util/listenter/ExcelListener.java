package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-08-03 21:08
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {

    /**
     * 数据列表
     */
    @Getter
    private final List<T> list = new ArrayList<>();
    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // Not need impl Method
    }
}
