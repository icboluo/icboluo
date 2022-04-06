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

2、${}非预编译（直接的sql拼接，不能防止sql注入）exa：select * from ${tableName}这个不行只能用默认的${value},用@param， @Param("tableName") String
tableName.从java到sql就好接受数据了，${tableName},表名是动态的，这个$传字符串需要手动添加''

$符号也有一定的用途，常用作写公共的sql，这个不是占位符，就相当于字符串拼接，可以使 sql更加动态

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

mybatis 可以设置无论有没有PageHelper.startPage 都可以在调用sql的时候分页，只要调用sql的参数中有pageSize, pageNum即可，需要在yml文件中进行开启配置

mybatis 从实体类中取值是根据get方法的

pageHelper可以暂时不执行，实现是先用PageHelper.getLocalPage 把数据缓存起来，然后clearPage 需要执行分页sql的时候恢复即可

## attention

mysql如果需要批量新增，可以用foreach标签或者case when，第一种的效率是可以接受的（需要开启批量操作）， foreach标签操作新增的时候有一个小问题，就是不能selective，不能利用到数据库的默认值

mybatis新增的时候，如果是自增主键，设置开启主键自增回显，会将主键写进参数中

mybatis中的item.id是用实体类中的get方法做映射的，相同的，java可以用get方法生成属性名

## PageInfo

如果分页list中的对象更改，需要将PageInfo的数据先缓存起来，然后重新组装PageInfo

## Count

mybatis的count函数可以重写

mysql的count方法如果效率过低可以重写，重写的sql可以和以前的sql有些差距，只需要计算总数即可

## 标签

mybaits if标签中 "test=date !=null and date !=''",不能这样写，不等于空字符串只针对于String类型

Integer collection 均不能这样写

mybatis 更改公共内容比如insert的时候需要增加注释，这些公共内容替换的时候要merge，不能直接替换，
insert增加了注释，才能知道返回了id

## 修改数据库

修改数据库字段后，要同步修改xml中的base sql和映射关系，否则，查出来的数据为空

# mybatis plus

mybatis plus 实体类名不能更改，是一个坑

insert的时候主键会传过去 insert id null...，即便设置了非null属性，需要设置id生成策略

insert自增主键的时候，可以返回主键id

# mybatis xml
mybatis-config.xml：全局配置文件（核心配置文件）作用：配置数据源（配置数据库连接信息）
```xml
<configuration>
加载外部资源文件： resource:默认引入classpath路径下的资源文件
<properties resource="jdbc.properties"></properties>
<settings>
<!--开启驼峰命名规则：不用as，相当于去掉数据库中的名字的下划线，如果不想用这个，用resultmap-->
<setting name="mapUnderscoreToCamelCase" value="true"/>
<!--配置延时加载，需要的时候再去查，修改idea默认select：settings java “tostring”去掉勾-->
<setting name="lazyLoadingEnabled" value="true"/>
</settings>
<!--配置别名:地址缩短，相当于数据库中的别名就是as-->
<typeAliases>
<typeAlias type="com.task.mybatis.pojo.User" alias="User"></typeAlias>
<!--所有实体都在这里面找，找到后直接用类名替换全路径-->
<package name="com.task.mybatis.pojo"></package>
</typeAliases>

    <!-- 配置环境：可以配置多个环境，default：配置某一个环境的唯一标识，表示默认使用哪个环境 -->
    <environments default="development">
        <!-- 配置环境,id:环境的唯一标识 -->
        <environment id="development">
            <!-- 事务管理器，type:使用jdbc的事务管理器 -->
            <transactionManager type="JDBC" />
            <!-- 数据源，type:池类型的数据源 -->
            <dataSource type="POOLED">
                <!-- 配置连接信息 -->
                <property name="driver" value="${driverClass}" />
                <property name="url" value="${url}" />......
    <!-- 配置映射文件：用来配置sql语句和结果集类型等 -->
    <mappers>
        <mapper resource="UserMapper.xml" />
        扫描指定包，并管理包中的所有映射文件
        <package name="com.task.mybatis.day02.dao"></package>
    </mappers>
</configuration>
```

映射文件：xxxMapper.xml，作用：配置sql语句、参数、结果集封装类型等\
mapper标签:配置各类声明，namespace防止crud语句的唯一标识被重复，需要设置空间名称
```xml
<mapper namespace="UserDaoMapper">
<select id="queryUserById" resultType="com.task.mybatis.pojo.User">全局缩短名字后可直接用User，也可以引用自定义的resultmap id
select * ,user_name as userName from tb_user where id=#{id};sql可以分开写
</select> 、update 、insert 、delete

  	<resultMap id="userResultMap" type="User" autoMapping="true"extends="a">自动映射自动填写自己没写的代码，如果resultmap别的地方也要用，使用extends不用id
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
    <select id="queryUserByIdOfOrder">#{id}...
    <sql id="selectSQL">
         id,user_name,password,name,age,sex,birthday,created,UPDATEd
     </sql>
     SELECT <include refid="SqlMapper.selectSQL"></include> FROM tb_user从其他xml中获取sql
    sql可以用<if>  choose when otherwise<otherwise>所有不满足条件才执行执行


        //配置全局配置文件
        String resource = "mybatis-config.xml";
        //通过工具栏获取配置文件的输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //通过工具类生成sqlsessionfactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        增删改操作需要事务的提交。设置自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //通过sqlsession 操作数据库
        User user = sqlSession.selectOne("UserMapper.queryUserById", 1L);
        可优化为：userDao2Mapper = sqlSession.getMapper(UserDao2Mapper.class);会通过动态代理生成一个代理的实现类
       要求namespace和param一致
```







