package com.icboluo.annotation;

import com.icboluo.BaseTest;
import com.icboluo.object.CodeName;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IdNameParserTest extends BaseTest {

    @SpyBean
    private BaseI18nService baseI18nService;

    @Override
    protected void childBefore() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "14");
        map.put("name", "zhang shan");
        Mockito.doReturn(map).when(baseI18nService).selectByKeys(Mockito.anyList());
    }

    @Test
    void parse() {
        A item = new A();
        CodeName codeName = new CodeName();
        codeName.setId("id");
        item.status = codeName;

        IdNameParser.parse(item);
        Assertions.assertEquals("14", item.getStatus().getName());
    }

    @Test
    void parseList() {
        A item1 = new A();
        CodeName codeName = new CodeName();
        codeName.setId("id");
        item1.status = codeName;
        A item2 = new A();
        CodeName codeName2 = new CodeName();
        codeName2.setId("id");
        item2.status = codeName2;

        List<A> list = Arrays.asList(item1, item2);
        IdNameParser.parse(list);
        Assertions.assertEquals("14", list.get(0).getStatus().getName());
        Assertions.assertEquals("zhang shan", list.get(1).getStatus().getName());
    }

    @Data
    public static class A {
        CodeName status;
        Integer number;
    }
}
