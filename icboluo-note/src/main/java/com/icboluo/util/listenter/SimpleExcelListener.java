package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;

/**
 * @author icboluo
 * @since 2023-07-05 18:55
 */
public class SimpleExcelListener<T> extends ExcelListener<T> {

    public SimpleExcelListener(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // Not need impl Method
    }
}
