package com.icboluo.util;

import com.icboluo.object.CodeName;
import com.icboluo.util.serialize.SerializedLambda;
import org.junit.jupiter.api.Test;

class SerializedLambdaTest {

    @Test
    void getColumnName() {
        String columnName = SerializedLambda.getColumnName(CodeName::getName);
        System.out.println("columnName = " + columnName);
    }
}
