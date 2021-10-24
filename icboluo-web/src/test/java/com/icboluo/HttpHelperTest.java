package com.icboluo;

import com.icboluo.util.HttpHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "7");
        String id = HttpHelper.sendPost(url, map);
        System.out.println("id = " + id);
    }
}
