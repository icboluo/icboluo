# 数据库

有的时候数据库里面存储单一汉字是可以的，没必要转换成为code；转换为code一般是学生code，会引出好多学生姓名等其他信息，用于抽取；单一字段可以不抽取

内嵌sqlite有点奇诡，不能直接放在resource包下面，放到下面造成查不出数据
sqlite的sql文件不能和mysql共享，但是可以复制黏贴；如果sql内容比较多，可以先导成文件格式

## ddl

Data Definition Language ddl 数据定义语言

## 事物常见地并发问题

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

## explain

mysql对于查询语句用explain进行优化

## 索引失效

当作为状态值的时候，如果该值的出现的比率较高，则索引失效

    首先进入该值的节点，然后进入下一个节点，这是用索引
    进入下一个节点（虽然节点数多，但是只走树的一个分支，很可能比较快），这是用全局扫描

索引要求离散度较高，才有区分度，没有使用索引是因为mysql优化认为不用索引效率更高

group by 和order by的时候，很容易造成效率低下，可以对count语句进行优化

- 联合索引的效率和普通索引的效率一致
- 联合索引（a，b，c）相当于创建了a、ab、abc索引，最左匹配，对于b相当于没有创建索引，所以，创建联合索引的时候 需要仔细点

uuid类型的主键，使用的时候用索引；自增类型的主键，使用的时候不用使用索引

mysql全表扫描对应explain中的all，是对数据进行一行一行的扫描

避免数据类型转换，会使索引失效

## 唯一约束

mysql 增加唯一约束有时候会失效，是因为一个字段为null，mysql认为，所有的null都是互不相同的

## left join

- left join and 中的and是先进行右表筛选，再进行总数据匹配，如果筛选结果为空，则left join的整个右表数据为空
- 业务中常进行整个数据筛选，用where比left join合适
- mysql 左模糊 like 语句可以使用 locate（相当于 substring ）等语句替代

## or

> or链接的时候，只有全部加索引索引才会生效，不建议用or；修改方式：

- 同字段用in代替
- 不同字段用union拼接

mysql union 作用就是拼接上面查询出的结果和下面查询出的结果， 查询结果不变，数据量增加

## 特殊sql

SELECT COALESCE(business_name,'no business_name') AS bus_coalesce FROM business WHERE id=1

查询，如果第一个结果为空，用第二个参数，如果第二个为空用下一个...

分组中可以使用，先求和，再求总和

使用场景：暂无，分组求和再代码中写更简单

## 建表

表字段用业务+字段属性...不要只用单纯的字段属性，不要觉得字段过长，这样，使用过程比较清晰

## 最佳实践

- 对公共sql的更改需要在mapper接口上增加注释，更改公共sql的时候，需要merge，不准直接copy覆盖

- 修改数据库字段的时候，需要修改整个xml，需要注意，不要漏掉sql
- 使用该字段的时候，需要判断是否为null，防止npe的情况发生

- 将多种类型（Integer,Sting,LocalDate,List）转换为字符串放入数据库，并且取出来，可以使用JSON.toJSONString(a) 和JSON.pares(b)
- 整体需要使用反射操作

## 索引

* not in 没有使用索引
* not exist 使用到了索引

必定出现的left join 中需要加上索引，不一定出现的不需要加，where条件同理

普通查询会用到覆盖索引，只要查询的字段都有索引就不会回表

子查询的结果集无法使用索引

hash算法无法范围查找，一般不用做mysql索引

缺点：

索引太多会增加执行计划生成时间，也会增加修改时间

b+ 树 ：多路查找树

b+树更适合范围查找，找到一个数据的时候，可以把data链表中的数据同时带出来

b树只能通过中序遍历

因为b+树可以一次查询多个数据，所以，b+树io次数远小于b树

b+树的数据都在data中，查询效率比较稳定

b+树非叶子节点没有data，可以把索引加载到内存中提高效率

mysql 的索引分为一级主键索引和二级索引，二级索引的叶子节点上存储的是主键

聚集索引：索引结构和数据一起存放的索引：主键索引；二级索引属于非聚集索引

覆盖索引就是where什么查出来什么。2个一样的，并且有索引

## 数据结构

B+树 数据存储在叶子节点；页分裂是针对于数据结构的紧凑而言的

B+树比B树的优点

B+树节点上没有存储数据，所以B+树每次可以读取更多的数据；相当于IO次数减少了

B+树遍历更加方便，B树需要遍历整棵树，B+树仅需要遍历叶子节点（B+数所有的叶子结点都在同一层，会用链表将他们串起来

B+树数据获取效率相当，基本不怎么变化，因为数据都存储在叶子节点上

## 主键

mysql无序主键会导致页分裂，页分裂会导致碎片数据

自增主键用光就不动了，会报主键重复异常

如果不指定主键，会有一个默认的row id作为主键，主键用光后更新操作会覆盖原有数据

### mysql自增主键重置：

删除数据：delete from crew_test

删除主键数据：truncate table crew_test

## select 语句

select * 语句：

io问题：增大网络开销 扩展性：增减字段难以控制（但是可以动态返回全部字段，有利有弊

阅读问题：读起来更加简洁（指定字段读起来更加流水账

索引问题：覆盖索引更可能出现（这个基本用不到吧

## 字段映射

tinyint == byte

mysql 日期格式用 time stramp，不要使用 date time（date time 的日期只不过更靠前而已，但是不包含时区

## sql

mybatis中批量sql是可以使用selective

select name ,sum(money) from test group by name with rollup

先分组，分组后对聚合函数sum再聚合一次

select coalesce(name，总金额;) ,sum (money) from test group by name with rollup

这样写更合适

mysql的日期格式比较可以先把时间转换为日期 data() 再between

## 不建议

不建议删除db中的约束，太难考虑所有的情况了；针对于保存的数据，新建表似乎最好

## 建议

修改表字段类型并没有太复杂

## mysql

mysql left join 语句需要增加索引提高执行效率

left join 右表的条件列上要加上索引

插入数据库的数据，不需要完整的展示出来

where条件中多过滤一些行，使驱动表小一点

```mysql
-- 这个是设置自动地增值为1，但是没有效果
alter table base_operate_log
    auto_increment = 1;
```

## 数据库设计的三大范式

1nf.原子性, 最小字段不可再分割；地址可以写成省市区+详细地址

2nf.自己做自己的事情;对于关联主键，要求其余字段不能对关联字段有部分依赖关系；建议重新设置主键

3nf.不要搞别人的东西；物体加一个id就行，再加name不是搞事情，再加一连串数据，太难维护了;对于非主属性，只依赖于主属性，不依赖于其他非主属性；建议分表

## Sql优化

        System.out.println("索引是找到键，找值，找键，找值，随机的，全表扫描是直接找值，有序的");
        System.out.println("应尽量避免在 where 子句中对字段进行 null 值判断，" +
                "否则将导致引擎放弃使用索引而进行全表扫描，可以通过设置字段默认值");
        System.out.println("用Where子句替换HAVING 子句 因为HAVING 只会在检索出所有记录之后才对结果集进行过滤");
        System.out.println("复合索引（先查第一个，第一个相同再查第二个）在查询中只对第一个有效果，");
        System.out.println("or 两边都需要索引，有一个没有索引就会导致全表扫描");
        System.out.println("mysql offset表示取出前m+n条数据，扔掉前m条，返回n条，大数据量慢");
        System.out.println("用exists代替in 用 not exists代替 not in");

## 易错点

mysql在处理字符串与整数数据对比的时候，会依次将字符串与整数数据对比，直到字符串不为整数的字母为止

简单来说，就是挨着遍历数字，直到非数字，发现有没有匹配的值

select 'abc'=1;-- 0 false
select '1abc'=1;--1 true
select 'abc'=0;---1 true
select 'a2bc'=2;--0 false
select '02a2bc'=2;--1 true

结论，禁止使用字符串类型和数字类型做比较；常见于db_str in(int,int,int)这种模式，因为数据库中的数据为1;2;3这种类型，传参为int类型，就会造成数据类型转换比较，不合理

in应该这么写

where find_in_set('1',replace(product1,';',','))

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

mysql 日期长短不一比较, 长地切短即可

## 注意

left join on 和where的区别，where纯过滤，left join on是将过滤的条件展示出来，不管on什么，后面有没有and，左表
全部数据均展示，只是右表数据有没有而已，即便on后面and左表，依然至少右表没有数据

## 死锁

mysql在同一个会话窗口中，由于只有一个事物在执行，因此不会发生死锁

同一个会话窗口是指同一个idea的console，同一个navicat的一个查询页面，java的同一个线程

## 连接池

hikari 默认访问后500ms 内如果不再访问数据库，会断掉连接，如果需要继续访问，会再次尝试访问一次，判断连接是否可用，整个过程耗时25ms左右；

也就是把定制的select 1 再执行一次
