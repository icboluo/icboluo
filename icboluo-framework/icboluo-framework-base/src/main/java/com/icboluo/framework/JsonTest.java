package com.icboluo.framework;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * json 转换为对象 ：
 * Student student = JSON.parseObject(jsonObject.toString(), Student.class);
 * String to java obj:
 * IcGeneralCO icGeneralCO = JSON.parseObject(s, IcGeneralCO.class);
 *
 * @author lp
 */

public class JsonTest {

    @Test
    public void test1() {
        String s = """
                {"data":1,"name":1}""";
        JSONObject jsonObject = JSONObject.parseObject(s);
        // ClassCastException
        JSONObject data = jsonObject.getJSONObject("data");
        System.out.println("data = " + data);
    }
}
