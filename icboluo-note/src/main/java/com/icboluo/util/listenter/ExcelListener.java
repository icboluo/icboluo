package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-04-05 18:58
 */
@Getter
public abstract class ExcelListener<T> extends AnalysisEventListener<T> {

    /**
     * 数据列表
     */
    @Getter
    private final List<T> list = new ArrayList<>();


    /**
     * 解析类型
     */
    protected final Class<T> clazz;

    /**
     * 获取头行数，用于Excel分析
     *
     * @return 头行数
     */
    protected Integer head;

    protected ExcelListener(Class<T> clazz) {
        this(clazz, 1);
    }

    protected ExcelListener(Class<T> clazz, Integer head) {
        this.clazz = clazz;
        this.head = head;
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        list.add(data);
    }
}
