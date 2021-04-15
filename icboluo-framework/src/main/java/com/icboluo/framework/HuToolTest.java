package com.icboluo.framework;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.icboluo.enumerate.StatusEnum;
import com.icboluo.object.ArchivesVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.*;

/**
 * 提供树结构，用作菜单展示
 *
 * @author icboluo
 * @date 2020-09-18 00:32
 */
@Slf4j
public class HuToolTest {
    @Test
    public void uuidTest() {
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.randomUUID());
//        注意 IdUtil.createSnowflake每次调用会创建一个新的Snowflake对象，
//        不同的Snowflake对象创建的ID可能会有重复，因此请自行维护此对象为单例，或者使用IdUtil.getSnowflake使用全局单例对象
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long l = snowflake.nextId();
        System.out.println("l = " + l);
    }

    @Test
    public void convertTest() {
        //数字转大写
        double a = 123456.01;
        System.out.println(Convert.digitToChinese(a));
        //输出: 壹拾贰万叁仟肆佰伍拾陆元零壹分

//转换为字符串
        int i = 1;
        String aStr = Convert.toStr(a);
//转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);
//转换为日期对象
        String dateStr = "2017-05-06";
        Date date = Convert.toDate(dateStr);
        System.out.println("转换为日期对象: " + date);
    }

    @Test
    public void beanTest() {
        ArchivesVO brand = new ArchivesVO();
        brand.setId(1L + "");
        brand.setName("华为");
//Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(brand);
        log.info("beanUtil bean to map:{}", map);
//Map转Bean
        ArchivesVO mapBrand = BeanUtil.toBean(map, ArchivesVO.class);
        log.info("beanUtil map to bean:{}", mapBrand);
//Bean属性拷贝
        ArchivesVO copyBrand = new ArchivesVO();
        BeanUtil.copyProperties(brand, copyBrand);
        log.info("beanUtil copy properties:{}", copyBrand);
    }

    @Test
    public void numberTest() {
        double n1 = 1.234;
        double n2 = 1.234;
        double result;
//对float、double、BigDecimal做加减乘除操作
        result = NumberUtil.add(n1, n2);
        result = NumberUtil.sub(n1, n2);
        result = NumberUtil.mul(n1, n2);
        result = NumberUtil.div(n1, n2);
//保留两位小数
        BigDecimal roundNum = NumberUtil.round(n1, 2);
        String n3 = "1.234";
//判断是否为数字、整数、浮点数
        NumberUtil.isNumber(n3);
        NumberUtil.isInteger(n3);
        NumberUtil.isDouble(n3);
    }

    @Test
    public void annoTest() {
        //获取指定类、方法、字段、构造器上的注解列表
        Annotation[] annotationList = AnnotationUtil.getAnnotations(HuToolTest.class, false);
        log.info("annotationUtil annotations:{}", annotationList);
//获取指定类型注解
        Slf4j api = AnnotationUtil.getAnnotation(HuToolTest.class, Slf4j.class);
        log.info("annotationUtil api value:{}", api.topic());
//获取指定类型注解的值
        Object annotationValue = AnnotationUtil.getAnnotationValue(HuToolTest.class, Slf4j.class);
    }

    @Test
    public void mapTest() {
        //将多个键值对加入到Map中
        Map<Object, Object> map = MapUtil.of(new String[][]{
                {"key1", "value1"},
                {"key2", "value2"},
                {"key3", "value3"}
        });
//判断Map是否为空
        MapUtil.isEmpty(map);
        MapUtil.isNotEmpty(map);

//        List集合拆分
        List<String> valList = new ArrayList<>();
//        List<List<String>> partitionList = Lists.partition(valList, 30);

        //数组转换为列表
        String[] array = new String[]{"a", "b", "c", "d", "e"};
        List<String> list = CollUtil.newArrayList(array);
//join：数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        log.info("collUtil join:{}", joinStr);
//将以连接符号分隔的字符串再转换为列表
        List<String> splitList = StrUtil.split(joinStr, ',');
        log.info("collUtil split:{}", splitList);
    }

    @Test
    public void enumTest() {
//        获得枚举名
        List<String> names = EnumUtil.getNames(StatusEnum.class);
        System.out.println("names = " + names);
        List<Object> types = EnumUtil.getFieldValues(StatusEnum.class, "chinese");
        System.out.println("types = " + types);
        Map<String, StatusEnum> enumMap = EnumUtil.getEnumMap(StatusEnum.class);
        System.out.println("enumMap = " + enumMap);
        Map<String, Object> enumMap2 = EnumUtil.getNameFieldMap(StatusEnum.class, "type");
    }
}
