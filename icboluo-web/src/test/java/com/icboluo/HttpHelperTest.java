package com.icboluo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HttpHelperTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sendGet() {
        String url = "http://localhost:7010/user/getUserNameById";
        String id = HttpHelper.sendGet(url, "id", "7");
        System.out.println("id = " + id);
    }

    @Test
    void sendPost() {
        String url = "http://localhost:7010/user/postUserNameById";
        String id = HttpHelper.sendPost(url, "id", "7");
        System.out.println("id = " + id);
    }

}