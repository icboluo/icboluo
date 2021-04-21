package com.icboluo.enumerate;

import org.junit.jupiter.api.Test;

class EnumServiceInterfaceTest {

    @Test
    void findEnumById() {
        ColorPieceService colorPieceService = new ColorPieceService();
        String enumById = colorPieceService.findEnumNameById(3);
        System.out.println(enumById);
    }
}