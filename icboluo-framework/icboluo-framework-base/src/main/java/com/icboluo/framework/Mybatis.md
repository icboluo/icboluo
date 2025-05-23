## 源码

Mybatis拥有MappedStatement

当Executor的实现类BaseExecutor执行query方法的时候，会使用MappedStatement.getBoundSql(param)方法动态的生成需要执行的SQL语句---BoundSql;
根据BoundSql和其他的条件生成CacheKey作为一级缓存的key;

BaseExecutor聚合了PerpetualCache(永久缓存)impl Cache,PerpetualCache内部聚合了HashMap(
进行了一层薄薄的扩展，操作cache就是操作map)，聚合了id（为什么需要这个id？）

查看createCacheKey函数可得：CacheKey最终聚合了id、limit、sql、param参数成为一个list，利用参数生成hashCode

## 语法

1、#{}预编译，就是sql中一个？传参一个怎么接受都可以全部都是行参，传参多了用@Param("userName") String userName

2、${}非预编译（直接的sql拼接，不能防止sql注入）exa：select * from ${tableName}这个不行只能用默认的${value},用@param，
@Param("tableName") String
tableName.从java到sql就好接受数据了，${tableName},表名是动态的，这个$传字符串需要手动添加''

$符号也有一定的用途，常用作写公共的sql，这个不是占位符，就相当于字符串拼接，可以使 sql更加动态

> mybatis 在实体类中不能使用数组，只能使用list，在xml中的写法为[0]

## 名词/标签

resultType:结果集类型

parameterType：插入语句的参数类型，使用动态代理之后，需要和mapper接口中的参数类型一致，可以省略。

useGeneratedKeys：开启主键自增回显，将自增长的主键值回显到形参中（即封装到User对象中）

keyColumn：数据库中主键的字段名称

keyProperty：pojo中主键对应的属性

<where>去多的and和or

<set>去，号

<foreach collection="ids" item="id" separator="," open="("    close=")">#{id}</foreach>

## PageHelper

mybatis 可以设置无论有没有PageHelper.startPage 都可以在调用sql的时候分页，只要调用sql的参数中有pageSize,
pageNum即可，需要在yml文件中进行开启配置

mybatis 从实体类中取值是根据get方法的

pageHelper可以暂时不执行，实现是先用PageHelper.getLocalPage 把数据缓存起来，然后clearPage 需要执行分页sql的时候恢复即可

## PageInfo

如果分页list中的对象更改，需要将PageInfo的数据先缓存起来，然后重新组装PageInfo

## attention

mysql如果需要批量新增，可以用foreach标签或者case when，第一种的效率是可以接受的（需要开启批量操作），
foreach标签操作新增的时候有一个小问题，就是不能selective，不能利用到数据库的默认值

mybatis新增的时候，如果是自增主键，设置开启主键自增回显，会将主键写进参数中

mybatis中的item.id是用实体类中的get方法做映射的，相同的，java可以用get方法生成属性名

mybatis jdbcType对传入的null值会有影响，如果不指定，有的情况下会报错（没遇到过

mybatis 的返回值不要用一个空类来接收，会返回空元素的

mybatis会对返回值进行封装，比如count会封装成Long(在map中)，转换成string会报错

mybatis 的select all 函数是需要无参构造的

## Count

mysql的count方法如果效率过低可以重写，重写的sql可以和以前的sql有些差距，只需要计算总数即可

## 标签

错误写法: <if test=date !=null and date !=''"></if>

不能这样写, !='' 只针对于String类型, Integer Collection 均不能这样写

mybatis 更改公共内容比如insert的时候需要增加注释，这些公共内容替换的时候要merge，不能直接替换，
insert增加了注释，才能知道返回了id

mybatis 不支持数组，支持List 使用 #{ids[0]}取值

## 修改数据库

修改数据库字段后，要同步修改xml中的base sql和映射关系，否则，查出来的数据为空

# mybatis plus

mybatis plus 实体类名不能更改，是一个坑

insert的时候主键会传过去 insert id null...，即便设置了非null属性，需要设置id生成策略

insert自增主键的时候，可以返回主键id

```xml

<mapper namespace="UserDaoMapper">
    <resultMap id="userResultMap" type="User" autoMapping="true" extends="a">
        自动映射自动填写自己没写的代码，如果resultmap别的地方也要用，使用extends不用id
        <!--配置主键映射关系column《圆柱》：数据库语句中的名字（as过后的），property《所有权》自己写的名字-->
        <id column="id" property="id"></id>
        <!--配置用户名的映射关系，这和上面一样啊-->
        <result column="user_name" property="userName"></result>
        association《联盟》把user放在上面的type：user里面，嵌套时，要打开automapping
        延迟加载：就是在需要用到数据时才进行加载，不需要用到数据时就不加载数据。延迟加载也称懒加载.
        好处：先从单表查询，需要时再从关联表去关联查询，大大提高数据库性能，因为查询单表比关联查询多表快。。
        坏处：用到数据时，才会进行数据库查询，这样在大批量数据查询时，因为查询工作也要消耗时间，所以可能造成用户等待时间变长
        select属性：调用指定sql语句来执行延迟加载
        column属性：延迟加载的sql语句中所需的参数
        <association property="user" javaType="User" autoMapping="true" select="queryUserByIdOfOrder" column="{id=user_id}>
    </resultMap>
</mapper>
```







