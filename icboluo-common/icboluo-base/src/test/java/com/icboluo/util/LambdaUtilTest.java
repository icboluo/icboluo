package com.icboluo.util;

import com.icboluo.object.CodeName;
import com.icboluo.util.serialize.LambdaUtil;
import org.junit.jupiter.api.Test;

class LambdaUtilTest {

    @Test
    void getColumnName() {
        String columnName = LambdaUtil.getColumnName(CodeName::getName);
        System.out.println("columnName = " + columnName);
    }
}
