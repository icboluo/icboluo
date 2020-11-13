## mybatis 生成工具介绍

使用example，会生成好多的sql

使用schema前缀 没用

select 增加 for update 是增加事务，并没有什么用

date and time api 是jdk1.8以后的date api

使用实际的列名，不进行驼峰映射

使用jpa注解，在实体类上增加注解

使用as别名查询，会给base result map 增加表名前缀的别名

## 语法

1、#{}预编译，就是sql中一个？传参一个怎么接受都可以全部都是行参，传参多了用@Param("userName") String userName

2、${}非预编译（直接的sql拼接，不能防止sql注入）exa：select * from ${tableName}这个不行只能用默认的${value},用@param，
@Param("tableName") String tableName.从java到sql就好接受数据了，${tableName},表名是动态的，这个$传字符串需要手动添加''

## 名词/标签

resultType:结果集类型

parameterType：插入语句的参数类型，使用动态代理之后，需要和mapper接口中的参数类型一致，可以省略。

useGeneratedKeys：开启主键自增回显，将自增长的主键值回显到形参中（即封装到User对象中）

keyColumn：数据库中主键的字段名称

keyProperty：pojo中主键对应的属性

<where>去多的and和or

<set>去，号

 <foreach collection="ids" item="id" separator="," open="("    close=")">#{id}</foreach>