package com.icboluo.test;

import java.util.Scanner;

/**
 * @author icboluo
 * @since 2020-08-03 14:01
 */
public class shiwu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        //事物常见的并发问题
        String 丢失更新 = "撤销一个事物的时候，其他的线程如果已经把事物提交，会覆盖已提交的数据";
        String 脏读 = "读到一个事物未commit的数据";
        String 不可重复读 = "一个事务执行相同的查询两次或两次以上，但是每次都得到不同的数据（重点是修改）";
        String 幻读 = "第一个线程去更新全表，第二个线程去新增一个，发现更新全表没有全部实现（重点是新增和删除）";
        //spring事物的隔离级别
        String 数据库默认级别 = "DEFAULT";
        String 读未提交 = "脏读、虚读/幻读、不可重复读都可能发生";
        String 读已提交 = "不可重复读和虚读有可能发生（锁定正在读取的行）";
        String 可重复读 = "幻读可能发生（锁定读取的所有行）";
        String 串型化的 = "避免以上所有问题（锁表）";
        //事务特性
        String 原子性 = "强调事务的不可分割";
        String 一致性 = "事务的执行的前后数据的完整性保持一致";
        String 隔离性 = "一个事务执行的过程中, 不应该受到其他事务的干扰";
        String 持久性 = "事务一旦结束, 数据就持久到数据库";
    }
}
