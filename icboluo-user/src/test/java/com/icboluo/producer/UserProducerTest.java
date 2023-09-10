package com.icboluo.producer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserProducerTest {
    private static final UserProducer userProducer = new UserProducer();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserNameById() {
        String userNameById = userProducer.getUserNameById(8);
        System.out.println("userNameById = " + userNameById);
    }
}
