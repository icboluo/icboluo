package com.icboluo.annotation;

import com.icboluo.BaseTest;
import com.icboluo.object.CodeName;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.List;

class IdNameParserTest extends BaseTest {

    @SpyBean
    private BaseI18nService baseI18nService;

    @Override
    protected void childBefore() {
        BaseI18n item1 = new BaseI18n();
        item1.setKey("id");
        item1.setName("14");
        BaseI18n item2 = new BaseI18n();
        item2.setKey("name");
        item2.setName("zhang shan");

        Mockito.doReturn(Arrays.asList(item1, item2)).when(baseI18nService).selectByKeys(Mockito.anyList());
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
        codeName2.setId("name");
        item2.status = codeName2;

        List<A> list = Arrays.asList(item1, item2);
        IdNameParser.parse(list);
        Assertions.assertEquals("14", list.get(0).getStatus().getName());
        Assertions.assertEquals("zhang shan", list.get(1).getStatus().getName());
    }

    @Data
    public static class A {
        @IdToName(data = IdNameEnum.I18N)
        CodeName status;
        Integer number;
    }
}
