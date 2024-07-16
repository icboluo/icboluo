package com.icboluo.a_sql;

import com.icboluo.entity.Province;
import com.icboluo.mapper.ProvinceMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author icboluo
 * @since 2022-06-13 21:38
 */
public class MyBatisTest {
    @Test
    public void test1() throws IOException {
        // 配置全局配置文件 通过工具类获取配置文件的输入流 该文件中拥有SQL的账号密码等设置
        InputStream inputStream = new ClassPathResource("mybatis.xml").getInputStream();
        // 一个SqlSessionFactory只能连接一个数据库实例（聚合了数据库配置或者聚合了数据库通道
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 增删改操作需要事务的提交。设置自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 通过 SqlSession 操作数据库,会通过动态代理生成一个代理的实现类
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        System.out.println(mapper.queryByAllField(new Province()).get(0));
        // 此块使用的是 namespace, 要求namespace和param一致
        System.out.println(sqlSession.selectList("com.icboluo.mapper.ProvinceMapper.queryByAllField", new Province()).get(0));

        sqlSession.commit();
        sqlSession.flushStatements();
        sqlSession.close();
    }
}
