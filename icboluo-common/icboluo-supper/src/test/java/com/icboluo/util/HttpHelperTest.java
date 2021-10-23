package com.icboluo.util;

import org.junit.jupiter.api.Test;

class HttpHelperTest {

    @Test
    void sendGet() {
        String url = "http://127.0.0.1:7010/user/getUserNameById";
        String res = HttpHelper.sendGet(url, "id", "7");
        assert res != null;
        System.out.println("res = " + res);
    }

    @Test
    void sendPost() {
        String url = "http://127.0.0.1:7010/user/postUserNameById";
        String res = HttpHelper.sendPost(url, 7);
        System.out.println("res = " + res);
    }
}
