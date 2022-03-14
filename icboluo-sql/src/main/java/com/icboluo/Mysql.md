## idea链接mysql

1.通过联网下载驱动

2.找到本地mysql的jar包，配置驱动就可以了

mysql union 作用就是拼接上面查询出的结果和下面查询出的结果， 查询结果不变，数据量增加

ctrl shift r 全局搜索 可以搜索数据库中的表的使用

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

## left join

- left join and 中的and是先进行右表筛选，再进行总数据匹配，如果筛选结果为空，则left join的整个右表数据为空
- 业务中常进行整个数据筛选，用where比left join合适
- mysql 左模糊 like 语句可以使用 locate（相当于 substring ）等语句替代

## or

> or链接的时候，只有全部加索引索引才会生效，不建议用or；修改方式：

- 同字段用in代替
- 不同字段用union拼接

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

聚集索引：索引结构数据一起存放的索引：主键索引

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

## mysql

mysql left join 语句需要增加索引提高执行效率

left join 右表的条件列上要加上索引

插入数据库的数据，不需要完整的展示出来

where条件中多过滤一些行，使驱动表小一点

数据库设计的三大范式

1.原子性

2.自己做自己的事情

3.不要搞别人的东西；物体加一个id就行，再加name不是搞事情，再加一连串数据，太难维护了