package com.icboluo.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigClientControllerTest {

    private static ConfigClientController configClientController = new ConfigClientController();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getConfigInfo() {
        String configInfo = configClientController.getConfigInfo();
        System.out.println("configInfo = " + configInfo);
    }
}