## 表名的设计

格式

    项目_用途/页面_具体信息         表注释
    eg: game_config_user_role       游戏--配置--用户角色
        game_config_i18n_item       游戏--配置--i18n配置
    省略项目前缀
        config_user_account         配置--用户账户
        base_http_msg               基础--HTTP消息

## 状态模式用于业务流程处理

当一个主业务有不同的业务流程/不同的当前处理人逻辑：
> 将当前处理人单独出来到一个表中，更好使用

```mysql
create table best_practice_cur_handler
(
    id             bigint auto_increment comment 'id'
        primary key,
    cur_handler    varchar(32)       not null comment '当前处理人',
    role           tinyint           not null comment '角色',
    is_take_effect tinyint default 1 not null comment '是否生效',
    main_id        bigint            null comment '主表id'
)
    comment '最佳实践-当前处理人';
```

## 数据类型

数据库日期格式建立有2种，一种天级别的date，一种秒级别的datetime，不要均使用datetime

## 异常

Cause: java.lang.IllegalArgumentException: argument type mismatch

mybatis select * from db,中db需要增加无参构造函数，否则会报上述错误

## SQL

select * from information schema.INNODB TRX;

查看sql运行

当此语句发现running的时候，说明有语句在运行；当多线程执行的时候，如果一直在running，说明有一个线程一直在占用

## 时间

为什么mybatis打印第一行日志的时候总是延迟10ms，不管是同步请求还是异步请求均会出现
