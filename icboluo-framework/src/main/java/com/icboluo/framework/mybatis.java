package com.icboluo.framework;
/*
mybatis-config.xml：全局配置文件（核心配置文件）作用：配置数据源（配置数据库连接信息）
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


映射文件：xxxMapper.xml，作用：配置sql语句、参数、结果集封装类型等
mapper标签:配置各类声明，namespace防止crud语句的唯一标识被重复，需要设置空间名称
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




引入log日志2个步骤：
1、在pom.xml中，引入slf4j的依赖
2、添加配置文件log4j.properties
.bak使配置文件无效

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

 */
public class mybatis {

}
