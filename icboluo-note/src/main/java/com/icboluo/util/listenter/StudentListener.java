package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.icboluo.object.excel.StudentExcel;

/**
 * @author icboluo
 * @since 2022-04-05 18:51
 */
public class StudentListener extends ExcelListener<StudentExcel> {

    @Override
    public void invoke(StudentExcel data, AnalysisContext context) {
        ReadRowHolder rrh = context.readRowHolder();
        Integer rowIndex = rrh.getRowIndex();
        if (rowIndex > head()) {
            super.getList().add(data);
        } else {
            System.out.println("进行head校验----成功");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public int head() {
        return 0;
    }
}