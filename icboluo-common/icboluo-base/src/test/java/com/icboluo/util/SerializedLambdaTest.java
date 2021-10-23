package com.icboluo.util;

import com.icboluo.object.ArchivesVO;
import com.icboluo.util.serialize.SerializedLambda;
import org.junit.jupiter.api.Test;

class SerializedLambdaTest {

    @Test
    void getColumnName() {
        String columnName = SerializedLambda.getColumnName(ArchivesVO::getName);
        System.out.println("columnName = " + columnName);
    }
}