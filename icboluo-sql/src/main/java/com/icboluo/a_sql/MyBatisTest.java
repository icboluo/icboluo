package com.icboluo.a_sql;

import com.icboluo.entity.Province;
import com.icboluo.mapper.ProvinceMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

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
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // 一个SqlSessionFactory只能连接一个数据库实例（聚合了数据库配置或者聚合了数据库通道
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过 SqlSession 操作数据库
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        System.out.println(mapper.queryByAllField(new Province()).get(0));
        // 此块使用的是 namespace
        System.out.println(sqlSession.selectList("com.icboluo.mapper.ProvinceMapper.queryByAllField", new Province()).get(0));

        sqlSession.commit();
        sqlSession.flushStatements();
        sqlSession.close();
    }
}
