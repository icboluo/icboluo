package com.icboluo.framework;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icboluo.object.IdName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * json 转换为对象 ：
 * Student student = JSON.parseObject(jsonObject.toString(), Student.class);
 * String to java obj:
 * IcGeneralCO icGeneralCO = JSON.parseObject(s, IcGeneralCO.class);
 *
 * @author lp
 */

public class JsonTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test1() {
        String s = """
                {"data":1,"name":1}""";
        JSONObject jsonObject = JSONObject.parseObject(s);
        // ClassCastException
        JSONObject data = jsonObject.getJSONObject("data");
        System.out.println("data = " + data);
    }

    @Test
    public void test2() throws JsonProcessingException {
        String str1 = """
                {"name":"John","age":18}""";
        // 相当于转换为 jsonObject
        JsonNode jsonNode = objectMapper.readTree(str1);
        System.out.println(jsonNode.get("name").asText());
        // json 转换为map
        Map<String, String> map1 = objectMapper.readValue(str1, new TypeReference<>() {
        });
        Map<String, String> map2 = JSON.parseObject(str1, new com.alibaba.fastjson.TypeReference<>() {
        });
        Assert.isTrue(map1.get("age").equals(map2.get("age")), "");
        // bean 转换为json
        IdName idName = new IdName(18, "age");
        String str2 = objectMapper.writeValueAsString(idName);
        // json 转换为bean
        IdName idNameVal = objectMapper.readValue(str2, IdName.class);
        Assert.isTrue(idNameVal.getId() == 18, "");
    }
}
