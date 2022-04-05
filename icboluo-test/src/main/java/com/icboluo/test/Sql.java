package com.icboluo.test;

/**
 * 数据库设计的三大范式
 * 1nf：原子性，最小字段不可再分割；地址可以写成省市区+详细地址
 * 2nf：对于关联主键，要求其余字段不能对关联字段有部分依赖关系；建议重新设置主键
 * 3nf：对于非主属性，只依赖于主属性，不依赖于其他非主属性；建议分表
 *
 * @author icboluo
 * @since 2020-08-03 14:40
 */
public class Sql {
    public static void main(String[] args) {

        //sql优化
        System.out.println("索引是找到键，找值，找键，找值，随机的，全表扫描是直接找值，有序的");
        System.out.println("应尽量避免在 where 子句中对字段进行 null 值判断，" +
                "否则将导致引擎放弃使用索引而进行全表扫描，可以通过设置字段默认值");
        System.out.println("用Where子句替换HAVING 子句 因为HAVING 只会在检索出所有记录之后才对结果集进行过滤");
        System.out.println("复合索引（先查第一个，第一个相同再查第二个）在查询中只对第一个有效果，");
        System.out.println("or 两边都需要索引，有一个没有索引就会导致全表扫描");
        System.out.println("mysql offset表示取出前m+n条数据，扔掉前m条，返回n条，大数据量慢");
        System.out.println("用exists代替in 用 not exists代替 not in");
    }
}
