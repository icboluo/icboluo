package com.icboluo.util;

import com.icboluo.object.ArchivesVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerializedLambdaTest {

    @Test
    void getColumnName() {
        String columnName = SerializedLambda.getColumnName(ArchivesVO::getName);
        System.out.println("columnName = " + columnName);
    }
}