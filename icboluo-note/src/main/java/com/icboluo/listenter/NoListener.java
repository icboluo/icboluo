package com.icboluo.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.icboluo.object.clientobject.RowCO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lp
 */
public class NoListener extends AnalysisEventListener<RowCO> {

    public List<RowCO> list = new ArrayList<>();

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public NoListener() {
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(RowCO data, AnalysisContext context) {
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println(JSON.toJSONString(list));
    }
}
