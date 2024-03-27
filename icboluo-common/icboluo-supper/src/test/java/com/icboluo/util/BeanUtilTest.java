package com.icboluo.util;

import com.github.pagehelper.PageInfo;
import com.icboluo.common.PageQuery;
import com.icboluo.object.CodeName;
import com.icboluo.object.IdName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BeanUtilTest {

    @Test
    void allIsNull() {
        int[] arr1 = new int[10];
        boolean allIsNullInt = BeanUtil.allIsNull(arr1);
        assertFalse(allIsNullInt);

        String[] arr2 = new String[10];
        boolean allIsNullStr = BeanUtil.allIsNull(arr2);
        assertTrue(allIsNullStr);
    }

    @Test
    void mergeProperties() {
        IdName idName = new IdName(14, null);
        CodeName codeName = new CodeName(null, "code");
        BeanUtil.mergeProperties(codeName, idName);

        assertEquals(idName.getName(), codeName.getName());
    }

    @Test
    void groupBySize() {
        List<List<Integer>> list1 = BeanUtil.groupBySize(Stream.iterate(0, i -> ++i).limit(10001), 100);
        assertTrue(list1.size() <= 100);

        List<List<Integer>> list2 = BeanUtil.groupBySize(Stream.iterate(0, i -> ++i).limit(9999), 100);
        assertTrue(list2.size() <= 100);
    }

    @Test
    void cleanFirstLastDoubleQuotes() {
        String str1 = BeanUtil.cleanFirstLastDoubleQuotes("\"temp\"");
        assertEquals("temp", str1);
        String str2 = BeanUtil.cleanFirstLastDoubleQuotes("\"temp");
        assertEquals("\"temp", str2);
        String str3 = BeanUtil.cleanFirstLastDoubleQuotes("temp\"");
        assertEquals("temp\"", str3);
        String str4 = BeanUtil.cleanFirstLastDoubleQuotes("\"te\"mp\"");
        assertEquals("te\"mp", str4);
    }

    @Test
    void batchConsumer() {
        List<Object> list = new ArrayList<>();
        BeanUtil.batchConsumer(Stream.iterate(0, i -> ++i).limit(10001).collect(Collectors.toList()), list::add);
        assertEquals(3, list.size());
    }

    @Test
    void batchConsumerSkipFail() {
        String str1 = "normal str";
        String str2 = "exception str contain *";
        List<String> successMsg = new ArrayList<>();
        List<String> failMsg = new ArrayList<>();
        BeanUtil.batchConsumerSkipFail(Arrays.asList(str1, str2), list -> insert(list, successMsg), failMsg::add);
        assertEquals(1, successMsg.size());
        assertEquals(successMsg.get(0), str1);

        assertEquals(1, failMsg.size());
        assertEquals(failMsg.get(0), str2);
    }

    private void insert(List<String> list, List<String> msg) {
        for (String str : list) {
            if (str.contains("*")) {
                throw new I18nException();
            }
        }
        msg.addAll(list);
    }

    @Test
    void reverseMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 17);
        map.put("b", 18);
        map.put("c", 19);
        Map<Integer, String> reverseMap = BeanUtil.reverseMap(map);
        assertEquals(map, BeanUtil.reverseMap(reverseMap));
    }

    @Test
    void fakePage() {
        PageInfo<Integer> pi1 = BeanUtil.fakePage(Stream.iterate(0, i -> ++i).limit(101).collect(Collectors.toList()), new PageQuery());
        assertEquals(101L, pi1.getTotal());
        assertEquals(11, pi1.getPages());

        PageInfo<Integer> pi2 = BeanUtil.fakePage(Stream.iterate(0, i -> ++i).limit(101).collect(Collectors.toList()));
        assertEquals(101L, pi2.getTotal());
        assertEquals(1, pi2.getPages());
    }

    @Test
    void pageInfoConvert() {
        List<Integer> source = Stream.iterate(0, i -> ++i).limit(101).collect(Collectors.toList());
        List<Integer> target = Stream.iterate(0, i -> ++i).limit(101).collect(Collectors.toList());
        PageInfo<Integer> pi = new PageInfo<>(source);
        pi.setTotal(7000);
        PageInfo<Integer> targetPi = BeanUtil.pageInfoConvert(pi, target);
        assertEquals(7000, targetPi.getTotal());
        assertEquals(101, targetPi.getPageSize());
        assertEquals(70, targetPi.getPages());
    }

    @Test
    void removeAllIgnoreCase() {
        ArrayList<String> bigList1 = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        ArrayList<String> smallList1 = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        BeanUtil.removeAllIgnoreCase(bigList1, smallList1);
        assertEquals(1, bigList1.size());

        ArrayList<String> bigList2 = new ArrayList<>(Arrays.asList("123", "abC"));
        ArrayList<String> smallList2 = new ArrayList<>(Arrays.asList("ABC", "321", "123", "d"));
        BeanUtil.removeAllIgnoreCase(bigList2, smallList2);
        assertEquals(0, bigList2.size());
    }

    @Test
    void parseJoin() {
        String str = "0;1;2;3;4;5;6;7;8;9";
        assertEquals(10, BeanUtil.parseJoin(str, Integer::new).size());
    }
}
